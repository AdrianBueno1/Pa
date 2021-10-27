import reducer from './reducer';
import * as actions from './actions';

test('BUY_COMPLETED', () => {

    const saleId = 1;

    //en este caso no es es necesario añadir 'lastSaleId: null'
    const initialState = {}

    //producimos un nuevo estado, pasandole el estado anterior y la accion
    const state = reducer(initialState, actions.buyCompleted(saleId));

    //comprobamos que el estado se modifica correctamente
    expect(state.lastSaleId).toEqual(saleId);

});