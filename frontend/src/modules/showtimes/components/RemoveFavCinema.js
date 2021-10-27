import React from 'react';
import {FormattedMessage} from 'react-intl';
import {useDispatch} from 'react-redux';
import * as actions from '../actions';
import common from '../../common';

const RemoveFavCinema = () => {

    const dispatch = useDispatch();

    return (

        <button type="button" className="btn btn-danger ml-3" 

            onClick={() => {
                common.localStorageUtils.removeKey('PAProject_pa17_favCinema');
                dispatch(actions.clearFavCinema());
            }}>

            <FormattedMessage id='project.global.buttons.removeFavCinema'/>

        </button>

    );

};


export default RemoveFavCinema;
