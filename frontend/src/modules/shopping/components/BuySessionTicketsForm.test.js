import React from 'react';
import {createStore} from 'redux';
import {Provider} from 'react-redux';
import {render, fireEvent} from '@testing-library/react';
import {createMemoryHistory} from 'history'

import BuySessionTicketsForm from './BuySessionTicketsForm';
import {IntlProvider} from 'react-intl';
import messages from '../../../i18n/messages';
import {Router} from 'react-router-dom';
import * as actions from '../actions';

const renderComponent = (component, initialState = {}) => {

    const store = createStore(() => initialState);
    store.dispatch = jest.fn();
    const history = createMemoryHistory();

    return {history, ...render(
        <Provider store={store}>
            <IntlProvider locale="en" messages={messages['en']}>
                <Router history={history}>
                    {component}
                </Router>
            </IntlProvider>
        </Provider>
    )};
}

afterEach(() => actions.buy.mockRestore());

test('buy - success', () => {

    const sessionId = 1;

    //No es necesario añadir nada al estado inicial
    const initialState = {shopping: {}};

    //espiamos la funcion del modulo y reemplazamos su implementacion original
    //para este test siempre se devuelve la funcion onSuccess
    const buySpy = jest.spyOn(actions, 'buy').mockImplementation(
        (_sessionId, _seats, _creditCard, onSuccess, _onErrors) =>
            onSuccess());

    const {getByLabelText, getByRole, history} = renderComponent(<BuySessionTicketsForm sessionId={sessionId}/>,
        initialState);

    //Obtenemos los elementos por rol y labels
    const seatsInput = getByLabelText('Seats:');
    const creditCardInput = getByLabelText('Credit card:');
    const buyButton = getByRole('button');

    const seats = 1;
    const creditCard = "1234567890123456";

    //Cambiamos el valor de los inputs
    fireEvent.change(seatsInput, {target: {value: seats}});
    fireEvent.change(creditCardInput, {target: {value: creditCard}});


    //Comprobamos que su valor sea el correcto
    //En este caso seatsInput tiene formato numerico
    expect(Number(seatsInput.value)).toEqual(seats);
    expect(creditCardInput.value).toEqual(creditCard);

    //Generamos el evento click en el boton de compra
    fireEvent.click(buyButton);

    //comprobamos los argumentos de la primera, y unica, llamada a la funcion mock
    expect(buySpy.mock.calls[0][0]).toEqual(sessionId);
    expect(buySpy.mock.calls[0][1]).toEqual(seats);
    expect(buySpy.mock.calls[0][2]).toEqual(creditCard);

    //el numero de entradas en el stack es correcto
    expect(history.length).toEqual(2);

    //la direccion actual es la correcta
    expect(history.location.pathname).toEqual('/shopping/purchase-completed');

});

test('buy - backend errors', () => {

    //Mismas explicaciones que antes
    const initialState = {shopping: {}};
    const backendError = "Some backend error";

    //para este test siempre se devuelve la funcion onErrors
    jest.spyOn(actions, 'buy').mockImplementation(
        (_sessionId, _seats, _creditCard, _onSuccess, onErrors) =>
            onErrors({globalError: backendError}));

    //Para este caso no es necesario enviar sessionId a BuySessionTicketsForm
    const {getByLabelText, getByRole, container, history} = 
        renderComponent(<BuySessionTicketsForm />, initialState);

    const seatsInput = getByLabelText('Seats:');
    const creditCardInput = getByLabelText('Credit card:');
    const buyButton = getByRole('button');

    const seats = 1;
    const creditCard = "1234567890123456";

    fireEvent.change(seatsInput, {target: {value: seats}});
    fireEvent.change(creditCardInput, {target: {value: creditCard}});
    fireEvent.click(buyButton);

    // Assertions common to the "successful" use case are not repeated here.
    expect(container).toHaveTextContent(backendError);
    expect(history.length).toEqual(1);
    expect(history.location.pathname).toEqual('/');

});
