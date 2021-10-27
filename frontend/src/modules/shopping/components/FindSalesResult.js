import React from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Sales from './Sales';

const FindSalesResult = () => {

    const saleSearch = useSelector(selectors.getSaleSearch);
    const dispatch = useDispatch();

    if (!saleSearch) {
        return null;
    }

    if (saleSearch.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.shopping.FindSalesResult.noSales'/>
            </div>
        );
    }

    return (

        <div>
            <Sales sales={saleSearch.result.items}/>
            <Pager 
                back={{
                    enabled: saleSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindSalesResultPage(saleSearch.criteria))}}
                next={{
                    enabled: saleSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindSalesResultPage(saleSearch.criteria))}}/>
        </div>

    );

}

export default FindSalesResult;