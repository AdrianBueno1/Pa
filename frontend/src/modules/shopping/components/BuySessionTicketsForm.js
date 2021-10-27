import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

const BuySessionTicketsForm = ({sessionId}) => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [seats, setSeats] = useState(1);
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {
            dispatch(actions.buy(sessionId, 
                seats, creditCard.trim(), 
                () => history.push('/shopping/purchase-completed'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (

        <div>
            <Errors errors={backendErrors}
                onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.BuyForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="seats" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.seats"/>:
                            </label>
                            <div className="col-md-2">
                                <input type="number" id="seats" className="form-control"
                                    value={seats}
                                    onChange={e => setSeats(Number(e.target.value))}
                                    autoFocus
                                    min="1"/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.incorrectSeats'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="creditCard" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.creditCard"/>:
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="creditCard" className="form-control"
                                    value={creditCard}
                                    onChange={e => setCreditCard(e.target.value)}
                                    required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.buy"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    );

}

export default BuySessionTicketsForm;