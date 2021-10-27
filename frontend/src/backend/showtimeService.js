import { config, appFetch } from './appFetch';

export const getMovieShowtimes = (cinemaId, date, onSuccess) => {
    
    let path = `/showtimes/cinemas/${cinemaId}/movieShowtimes?date=`;
    path += date ? (new Date(date)).getTime() : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findByMovieId = (id, onSuccess) => 
    appFetch(`/showtimes/movies/${id}`, config('GET'), onSuccess);


export const findBySessionId = (id, onSuccess, onErrors) => 
    appFetch(`/showtimes/sessions/${id}`, config('GET'), onSuccess, onErrors);


export const findAllCities = (onSuccess) => 
    appFetch('/showtimes/cities', config('GET'), onSuccess);

export const findCinemasByCityId = (cityId, onSuccess) => {
    
    let path = `/showtimes/cinemas?cityId=${cityId}`;

    appFetch(path, config('GET'), onSuccess);
}