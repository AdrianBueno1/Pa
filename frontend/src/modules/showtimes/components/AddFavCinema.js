import React from 'react';
import PropTypes from 'prop-types';
import {FormattedMessage} from 'react-intl';
import {useDispatch} from 'react-redux';
import * as actions from '../actions';
import common from '../../common';


const AddFavCinema = (cinema) => {

    const dispatch = useDispatch();

    return (

        <button type="button" className="btn btn-success ml-3" 

            onClick={() => {
                console.log(cinema)
                common.localStorageUtils.addObject('PAProject_pa17_favCinema', cinema);
                dispatch(actions.setFavCinema(cinema.cityId, cinema.cinemaId));
            }}>

            <FormattedMessage id='project.global.buttons.addFavCinema'/>

        </button>

    );

};

AddFavCinema.propTypes = {
    cinema: PropTypes.object
};

export default AddFavCinema;
