import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

export const getShowtimesCompleted = (movies, showtimesDate, cinema) => ({
    type: actionTypes.GET_SHOWTIMES_COMPLETED,
    showtimesDate,
    movies,
    cinema
});

export const getShowtimes = (cityId, cinemaId, cinemaName, showtimesDate) => dispatch => {

    dispatch(clearShowtimes());

    backend.showtimeService.getMovieShowtimes(cinemaId, showtimesDate, 
        result => dispatch(getShowtimesCompleted(result, showtimesDate, {cityId, cinemaId, cinemaName})));
}

const clearShowtimes = () => ({
    type: actionTypes.CLEAR_SHOWTIMES
});

const findMovieByIdCompleted = movie => ({
    type: actionTypes.FIND_MOVIE_BY_ID_COMPLETED,
    movie
});

export const findMovieById = id => dispatch => {
    backend.showtimeService.findByMovieId(id,
        movie => dispatch(findMovieByIdCompleted(movie)));
}

export const clearMovie = () => ({
    type: actionTypes.CLEAR_MOVIE
});


const findSessionByIdCompleted = session => ({
    type: actionTypes.FIND_SESSION_BY_ID_COMPLETED,
    session
});

export const findSessionById = (id, onErrors) => dispatch => {
    backend.showtimeService.findBySessionId(id,
        session => dispatch(findSessionByIdCompleted(session)),
        onErrors);
}

export const clearSession = () => ({
    type: actionTypes.CLEAR_SESSION
});

const findAllCitiesCompleted = cities => ({
    type: actionTypes.FIND_ALL_CITIES_COMPLETED,
    cities
}); 

export const findAllCities = () => (dispatch, getState) => {

    const cities = selectors.getCities(getState());

    if (!cities) {

        backend.showtimeService.findAllCities(
            cities => dispatch(findAllCitiesCompleted(cities))
        );
        
    }

}

const findCinemasByCityIdCompleted = cinemas => ({
    type: actionTypes.FIND_CINEMAS_BY_CITY_ID_COMPLETED,
    cinemas
}); 

export const findCinemasByCityId = id => dispatch => {
    backend.showtimeService.findCinemasByCityId(id,
        cinemas => dispatch(findCinemasByCityIdCompleted(cinemas)));
}



const setFavCinemaCompleted = favCinema => ({
    type: actionTypes.SET_FAV_CINEMA_COMPLETED,
    favCinema
}); 

export const clearFavCinema = () => ({
    type: actionTypes.CLEAR_FAV_CINEMA
});

export const setFavCinema = (favCityId, favCinemaId) => dispatch => 
    dispatch(setFavCinemaCompleted({favCityId, favCinemaId}))

export const findFavCinemaShowimes = cinema => (dispatch, getState) => {
    dispatch(findCinemasByCityId(cinema.cityId));
    dispatch(setFavCinema(cinema.cityId, cinema.cinemaId));

    dispatch(getShowtimes(cinema.cityId,
        cinema.cinemaId, 
        cinema.cinemaName, 
        selectors.getShowtimesDate(getState())));
}