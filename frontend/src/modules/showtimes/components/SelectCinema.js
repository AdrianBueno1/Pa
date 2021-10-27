import React, {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import CitySelector from './CitySelector';
import CinemaSelector from './CinemaSelector';
import * as actions from '../actions';
import * as selectors from '../selectors';

const SelectCinema = () => {

    const dispatch = useDispatch();
    const [cityId, setCityId] = useState('');
    const [cinemaId, setCinemaId] = useState('');
    const showtimesDate = useSelector(selectors.getShowtimesDate);
    const {favCityId, favCinemaId} = useSelector(selectors.getFavCinema);
    const history = useHistory();

    const handleCitySelect = event => {
        setCityId(event.target.value)
        setCinemaId("")

        dispatch(actions.findCinemasByCityId(toNumber(event.target.value)));
    }

    const handleCinemaSelect = event => {
        setCinemaId(event.target.value)
        
        
        dispatch(actions.getShowtimes(Number(cityId),
                                    Number(event.target.value), 
                                    event.target[event.target.selectedIndex].text, 
                                    showtimesDate));
        history.push('/');
    }

    //solo ocurre en el momento inicial, donde los dos selectores de ciudades y cine 
    //aun no tienen valor, y ademas tenemos valores para el cine favorito
    if (!cityId && !cinemaId && favCityId && favCinemaId) {
        setCityId(favCityId);
        setCinemaId(favCinemaId);
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (

        <div className="form-inline mt-2 mt-md-0">

            <CitySelector id="cityId" className="custom-select my-1 mr-sm-2"
                value={cityId} onChange={e => handleCitySelect(e)}/>

            <CinemaSelector id="cinemaId" className="custom-select my-1 mr-sm-2"
                value={cinemaId} onChange={e => handleCinemaSelect(e)}/>

        </div>

    );

}

export default SelectCinema;
