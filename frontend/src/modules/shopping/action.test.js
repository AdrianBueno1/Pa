import configureMockStore from 'redux-mock-store';
import thunk from 'redux-thunk';

import * as actions from './actions';
import backend from '../../backend';

const middlewares = [thunk]
const mockStore = configureMockStore(middlewares);

afterEach(() => backend.shoppingService.buy.mockRestore());

test('buy - success', () => {

    const saleId = 1;

    //espiamos la funcion del modulo y reemplazamos su implementacion original
    //para este test siempre se devuelve la funcion onSuccess
    const backendBuySpy = jest.spyOn(backend.shoppingService, 'buy').mockImplementation(
        (_sessionId, _seats, _creditCard, onSuccess, _onErrors) => 
            onSuccess({id: saleId}));

    const sessionId = 1;
    const seats = 1;
    const creditCard = "1234567890123456";

    //creamos una funcion mock sin ninguna implementacion (devuelven undefined al ejecutarse)
    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();

    //ejecutamos la accion buy pasandole los parametros y las funciones mock
    //esta accion ejecutara la funcion mock backendBuySpy que reemplaza a la original en backend
    //este funcion mock nueva siempre devuelve el id saleId = 1, y por lo tanto ejecutando el 
    //metodo dispatch(buyCompleted(id));
    const action = actions.buy(sessionId, seats, creditCard, onSuccess, onErrors);

    //la accion resultado esperada
    const expectedActions = [actions.buyCompleted(saleId)];

    //creamos un mock de la store
    const store = mockStore({});

    //enviamos la accion a la store, como esta accion es la normal definida en actions.js
    //debería la accion actions.buyCompleted(saleId)
    store.dispatch(action);
    
    //comprobamos los argumentos de la primera, y unica, llamada a la funcion mock
    expect(backendBuySpy.mock.calls[0][0]).toEqual(sessionId);
    expect(backendBuySpy.mock.calls[0][1]).toEqual(seats);
    expect(backendBuySpy.mock.calls[0][2]).toEqual(creditCard);

    //comprobamos que las acciones obtenidas son iguales a las esperadas
    expect(store.getActions()).toEqual(expectedActions);

    //comprobamos que solo se llamó a la funcion onSuccess
    expect(onSuccess).toHaveBeenCalled();
    expect(onErrors).not.toHaveBeenCalled();

});

test('buy - backend errors', () => {

    const backendErrors = {globalError: "Some backend error"};
    
    //misma explicacion que antes, solo que ahora llamamos a la funcion onErrors
    jest.spyOn(backend.shoppingService, 'buy').mockImplementation(
        (_sessionId, _seats, _creditCard, _onSuccess, onErrors) => 
            onErrors(backendErrors));

    const sessionId = 1;
    const seats = 1;
    const creditCard = "1234567890123456";

    const onSuccess = jest.fn(); 
    const onErrors = jest.fn();

    const action = actions.buy(sessionId, seats, creditCard, onSuccess, onErrors);

    //en este caso al ser un error no hay una accion esperada
    const expectedActions = [];

    const store = mockStore({});
    store.dispatch(action);


    expect(store.getActions()).toEqual(expectedActions);

    //comprobamos que solo se llama a la funcion onErrors
    expect(onSuccess).not.toHaveBeenCalled();
    expect(onErrors).toHaveBeenCalled();

    //comprobamos que el argumento de la primera llamada es el correcto
    expect(onErrors.mock.calls[0][0]).toEqual(backendErrors);

});