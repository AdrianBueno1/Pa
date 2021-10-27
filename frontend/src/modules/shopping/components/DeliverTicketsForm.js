import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';

const DeliverTicketsForm = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [saleId, setSaleId] = useState(1);
    const [creditCard, setCreditCard] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.deliver(saleId, creditCard.trim(),
                () => history.push('/'),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (

        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>

            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.DeliverForm.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                        className="needs-validation" noValidate 
                        onSubmit={(e) => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="saleId" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.saleId"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="saleId" className="form-control"
                                    value={saleId}
                                    onChange={e => setSaleId(Number(e.target.value))}
                                    autoFocus
                                    required
                                    min="1" />
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
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
                                    <FormattedMessage id="project.global.buttons.deliver"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    );

}

export default DeliverTicketsForm;
