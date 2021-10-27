import React from 'react';
import {FormattedDate} from 'react-intl';
import PropTypes from 'prop-types';
import common from '../../common';

const DateSelector = (selectProps) => {

    const dates = common.dateUtils.getArrayWithNextDays(7);

    return(
        <select {...selectProps}>
            {dates.map(date =>
                <FormattedDate value={date} key={date.getTime()}>
                    {dateAsString =>
                        (<option value={common.dateUtils.formatDate(date)}>
                            {dateAsString}
                        </option>)
                    }
                </FormattedDate>
            )}
        </select>
    );

}

DateSelector.propTypes = {
    selectProps: PropTypes.object
};

export default DateSelector;
