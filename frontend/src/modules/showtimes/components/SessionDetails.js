import React, {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber, FormattedDate, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import {Errors} from '../../common';
import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BackLink} from '../../common';
import {BuySessionTicketsForm} from '../../shopping';


const SessionDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const isUserRole = useSelector(users.selectors.isUser);
    const session = useSelector(selectors.getSession);
    const dispatch = useDispatch();
    const [backendErrors, setBackendErrors] = useState(null);
    const {id} = useParams();

    useEffect(() => {

        const sessionId = Number(id);

        if (!Number.isNaN(sessionId)) {
            dispatch(actions.findSessionById(
                sessionId,
                errors => setBackendErrors(errors)));
        }

        return () => dispatch(actions.clearSession());

    }, [id, dispatch]);

    if (backendErrors) {
        return (
            <div>
                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            </div>
        );
    }
    
    if (!session) {
        return null;
    }

    return (
        
        <div>
            
            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title font-weight-bold">{session.movieName}</h5>
                    <p className="card-subtitle text-muted">{session.cinemaName} - {session.hallName}</p>
                    <br />
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.date'/>:&nbsp;
                            <FormattedDate value={new Date(session.date)}/> - <FormattedTime value={new Date(session.date)}/>
                    </p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.duration'/>:&nbsp;
                            <FormattedNumber value={session.movieDuration}/>&nbsp;<FormattedMessage id='project.global.others.minutes'/>
                    </p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.availableSeats'/>:&nbsp;
                            <FormattedNumber value={session.availableSeats}/>
                    </p>
                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.price'/>
                        : <FormattedNumber value={session.price}/>€
                    </p>
                </div>
            </div>

            {loggedIn && isUserRole &&
                <div>
                    <br/>
                    <BuySessionTicketsForm sessionId={id}/>
                </div>
            }

        </div>

    );

}

export default SessionDetails;