const getModuleState = state => state.showtimes;

export const getMovies = state =>
    getModuleState(state).movies;

export const getMovie = state =>
    getModuleState(state).movie;

export const getShowtimesDate = state =>
    getModuleState(state).showtimesDate;

export const getSession = state =>
    getModuleState(state).session;

export const getCities = state => 
    getModuleState(state).cities;

export const getCinemas = state => 
    getModuleState(state).cinemas;

export const getCinema = state => 
    getModuleState(state).cinema;

export const getCinemaId = state => 
    getModuleState(state).cinema.cinemaId;

export const getCityId = state => 
    getModuleState(state).cinema.cityId;

export const getFavCinema = state => 
    getModuleState(state).favCinema;

export const getFavCinemaId = state => 
    getModuleState(state).favCinema.favCinemaId;
