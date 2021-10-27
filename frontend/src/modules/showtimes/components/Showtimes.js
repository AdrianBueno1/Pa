import React from 'react';
import {FormattedMessage} from 'react-intl';
import {useSelector, useDispatch} from 'react-redux';

import * as selectors from '../selectors';
import Movies from './Movies';
import * as actions from '../actions';
import DateSelector from "./DateSelector";
import AddFavCinema from './AddFavCinema';
import RemoveFavCinema from './RemoveFavCinema';

const Showtimes = () => {
    const dispatch = useDispatch();
    const showtimesDate = useSelector(selectors.getShowtimesDate);
    const {cityId, cinemaId, cinemaName} = useSelector(selectors.getCinema);
    const movies = useSelector(selectors.getMovies);
    const favCinemaId = useSelector(selectors.getFavCinemaId);

    if (!movies || !cinemaId) {

        return (
            <div className="text-center">
                <FormattedMessage id="project.app.Showtimes.welcome"/>
            </div>
        );
    }

    return (
        <div>
            
            <div className="form-inline">
                <h1 className="font-weight-bold">{cinemaName}</h1>

                {favCinemaId === cinemaId ?

                <RemoveFavCinema/>

                :

                <AddFavCinema cityId={cityId} cinemaId={cinemaId} cinemaName={cinemaName} />

                }

            </div>
            <br />

            <DateSelector id="showtimesDate" className="custom-select my-2 mr-sm-2 col-2" 
                value={showtimesDate} 
                onChange={e => dispatch(actions.getShowtimes(cityId, cinemaId, cinemaName, e.target.value))}
            />
        
            <Movies movies={movies} />
        </div>
    );
};

export default Showtimes;