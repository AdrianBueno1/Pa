import React from 'react';
import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

const Sales = ({sales}) => (

    <table className="table table-striped table-hover text-center">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.saleDate'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.cinema'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.title'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.tickets'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.totalPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.sessionDate'/>
                </th>
            </tr>
        </thead>

        <tbody>
            {sales.map((sale, index) => 
                <tr key={index}>
                    <td>
                        <FormattedDate value={new Date(sale.saleDate)}/> - <FormattedTime value={new Date(sale.saleDate)}/>
                    </td>
                    <td>{sale.cinemaName}</td>
                    <td>{sale.movieTitle}</td>
                    <td><FormattedNumber value={sale.seats}/></td>
                    <td><FormattedNumber value={sale.totalPrice}/> €</td>
                    <td>
                        <FormattedDate value={new Date(sale.sessionDate)}/> - <FormattedTime value={new Date(sale.sessionDate)}/>
                    </td>
                </tr>
            )}
        </tbody>

    </table>

);

Sales.propTypes = {
    sales: PropTypes.array.isRequired
};

export default Sales;

