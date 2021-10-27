import React, {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {BrowserRouter as Router} from 'react-router-dom';

import Header from './Header';
import Body from './Body';
import Footer from './Footer';
import users from '../../users';
import showtimes from '../../showtimes';
import common from '../../common';

const App = () => {

    const dispatch = useDispatch();
    const object = common.localStorageUtils.getObject('PAProject_pa17_favCinema')

    useEffect(() => {

        dispatch(users.actions.tryLoginFromServiceToken(
            () => dispatch(users.actions.logout())));

        dispatch(showtimes.actions.findAllCities());
        
        //si tenemos un cine como favorito, y con los atributos necesarios 
        if (object && object.cityId && object.cinemaId && object.cinemaName) {
            dispatch(showtimes.actions.findFavCinemaShowimes(object));
        }

    });

    return (
        <div>
            <Router>
                <div>
                    <Header/>
                    <Body/>
                </div>
            </Router>
            <Footer/>
        </div>
    );

}
    
export default App;