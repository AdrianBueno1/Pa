import React from 'react';
import PropTypes from 'prop-types';
import { FormattedMessage } from 'react-intl';
import {SessionLink} from '../../common';
import {MovieLink} from '../../common';

const Movies = ({movies}) => (
    <table className="table table-striped table-hover" >

        <thead>
            <tr>
                <th scope="col" style={{width: "30%"}}>
                    <FormattedMessage id='project.global.fields.title'/>
                </th>
                <th scope="col" style={{width: "70%"}}>
                    <FormattedMessage id='project.global.fields.sessions'/>
                </th>
            </tr>
        </thead>

        <tbody> 
            { movies.map(movie => 
                <tr key={movie.id}>
                    <td><MovieLink id={movie.id} name={movie.movieName}/></td>
                    <td>   
                        {movie.sessions.map(session => 
                            <SessionLink key={session.id} id={session.id} date={session.date}/>
                        )}
                    </td>
                </tr>
            )}
        </tbody>

    </table>
    
);
  
Movies.propTypes = {
    movies: PropTypes.array
};

export default Movies;