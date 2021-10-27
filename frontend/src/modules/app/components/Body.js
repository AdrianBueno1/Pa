import React from 'react';
import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {MovieDetails, SessionDetails} from '../../showtimes';
import {PurchaseCompleted, FindSales, FindSalesResult, DeliverTickets} from '../../shopping';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const isUserRole = useSelector(users.selectors.isUser);
    const isTicketSellerRole = useSelector(users.selectors.isTicketSeller);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/showtimes/sessions/:id"><SessionDetails/></Route>
                <Route exact path="/showtimes/movies/:id"><MovieDetails/></Route>
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                {loggedIn && isUserRole && <Route exact path="/shopping/purchase-completed"><PurchaseCompleted/></Route>}
                {loggedIn && isUserRole && <Route exact path="/shopping/find-sales"><FindSales/></Route>}
                {loggedIn && isUserRole && <Route exact path="/shopping/find-sales-result"><FindSalesResult/></Route>}
                {loggedIn && isTicketSellerRole && <Route exact path="/shopping/deliver-tickets"><DeliverTickets/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
