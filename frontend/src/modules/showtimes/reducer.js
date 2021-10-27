import { combineReducers } from 'redux';

import * as actionTypes from './actionTypes';
import common from '../common';

const initialState = {
    movies: null,
    //el estado inicial de la fecha es el dia actual
    showtimesDate: common.dateUtils.formatDate(new Date()),
    movie: null,
    session: null,
    cities: null,
    cinemas: null,
    cinema: {
        cityId: null,
        cinemaId: null, 
        cinemaName: null},
    favCinema: {
        favCityId: null,
        favCinemaId: null},
};

const movies = (state = initialState.movies, action) => {

    switch (action.type) {

        case actionTypes.GET_SHOWTIMES_COMPLETED:
            return action.movies;

        default:
            return state;
    }

}

const showtimesDate = (state = initialState.showtimesDate, action) => {

    switch (action.type) {

        case actionTypes.GET_SHOWTIMES_COMPLETED:
            return action.showtimesDate;


        default:
            return state;
    }

}

const movie = (state = initialState.movie, action) => {

    switch (action.type) {

        case actionTypes.FIND_MOVIE_BY_ID_COMPLETED:
            return action.movie;

        case actionTypes.CLEAR_MOVIE:
            return initialState.movie;

        default:
            return state;

    }

}

const session = (state = initialState.session, action) => {

    switch (action.type) {

        case actionTypes.FIND_SESSION_BY_ID_COMPLETED:
            return action.session;

        case actionTypes.CLEAR_SESSION:
            return initialState.session;

        default:
            return state;

    }

}

const cities = (state = initialState.cities, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_CITIES_COMPLETED:
            return action.cities;

        default:
            return state;

    }

}

const cinemas = (state = initialState.cinemas, action) => {

    switch (action.type) {

        case actionTypes.FIND_CINEMAS_BY_CITY_ID_COMPLETED:
            return action.cinemas;

        default:
            return state;

    }

}

const cinema = (state = initialState.cinema, action) => {

    switch (action.type) {

        case actionTypes.GET_SHOWTIMES_COMPLETED:
            return action.cinema;

        default:
            return state;
    }

}

const favCinema = (state = initialState.favCinema, action) => {

    switch (action.type) {

        case actionTypes.SET_FAV_CINEMA_COMPLETED:
            return action.favCinema;
        
        case actionTypes.CLEAR_FAV_CINEMA:
            return initialState.favCinema;

        default:
            return state;
    }

}

const reducer = combineReducers({
    movies,
    showtimesDate,
    movie,
    session,
    cities,
    cinemas,
    cinema,
    favCinema,
});

export default reducer;