import * as roles from './roles';

const getModuleState = state => state.users;

export const getUser = state => 
    getModuleState(state).user;

export const isLoggedIn = state =>
    getUser(state) !== null

export const getUserName = state => 
    isLoggedIn(state) ? getUser(state).userName : null;

export const isUser = state =>
    isLoggedIn(state) && getUser(state).role === roles.USER_ROLE

export const isTicketSeller = state =>
    isLoggedIn(state) && getUser(state).role === roles.TICKET_SELLER_ROLE

