import React from 'react';
import PropTypes from 'prop-types';
import {FormattedTime} from 'react-intl';

import {Link} from 'react-router-dom';

const SessionLink = ({id, date}) => {

    let newDate = new Date(date);

    return (
        <Link to={`/showtimes/sessions/${id}`} className="btn btn-info mr-sm-2">
            <FormattedTime value={newDate}/>
        </Link>
    );

}

SessionLink.propTypes = {
    id: PropTypes.number.isRequired,
    date: PropTypes.number.isRequired,
};

export default SessionLink; 