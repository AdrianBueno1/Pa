import React from 'react';
import PropTypes from 'prop-types';

import {Link} from 'react-router-dom';

const MovieLink = ({id, name}) => {
    
    return (
        <Link to={`/showtimes/movies/${id}`}>
            {name}
        </Link>
    );

}

MovieLink.propTypes = {
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
};

export default MovieLink; 