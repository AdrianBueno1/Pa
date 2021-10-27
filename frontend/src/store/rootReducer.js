import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import showtimes from '../modules/showtimes';
import shopping from '../modules/shopping';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    showtimes: showtimes.reducer,
    shopping: shopping.reducer
});

export default rootReducer;
