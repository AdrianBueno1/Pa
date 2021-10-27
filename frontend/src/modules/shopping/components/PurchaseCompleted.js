import React from 'react';
import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';

const PurchaseCompleted = () => {

    const saleId = useSelector(selectors.getLastSaleId);

    if (!saleId) {
        return null;
    }
    
    return (
        <div className="alert alert-success" role="alert">
            <FormattedMessage id="project.shopping.PurchaseCompleted.purchaseOrderGenerated"/>:
            &nbsp;{saleId}
        </div>
    );

}

export default PurchaseCompleted;