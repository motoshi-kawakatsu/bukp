﻿/*
    *
    * Wijmo Library 5.20161.138
    * http://wijmo.com/
    *
    * Copyright(c) GrapeCity, Inc.  All rights reserved.
    *
    * Licensed under the Wijmo Commercial License.
    * sales@wijmo.com
    * http://wijmo.com/products/wijmo-5/license/
    *
    */
/*
 * Wijmo culture file: en-CA (English (Canada))
 */
var wijmo;
(function (wijmo) {
    wijmo.culture = {
        Globalize: {
            numberFormat: {
                '.': '.',
                ',': ',',
                percent: { pattern: ['-n %', 'n %'] },
                currency: { decimals: 2, symbol: '$', pattern: ['-$n', '$n'] }
            },
            calendar: {
                '/': '-',
                ':': ':',
                firstDay: 0,
                days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
                daysAbbr: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                monthsAbbr: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                am: ['AM', 'A'],
                pm: ['PM', 'P'],
                eras: ['A.D.'],
                patterns: {
                    d: 'yyyy-MM-dd', D: 'MMMM d, yyyy',
                    f: 'MMMM d, yyyy h:mm tt', F: 'MMMM d, yyyy h:mm:ss tt',
                    t: 'h:mm tt', T: 'h:mm:ss tt',
                    m: 'd MMMM', M: 'd MMMM',
                    y: 'MMMM, yyyy', Y: 'MMMM, yyyy',
                    g: 'yyyy-MM-dd h:mm tt', G: 'yyyy-MM-dd h:mm:ss tt',
                    s: 'yyyy"-"MM"-"dd"T"HH":"mm":"ss'
                },
                fiscalYearOffsets: [3, 0],
            }
        },
        MultiSelect: {
            itemsSelected: '{count:n0} items selected'
        },
        FlexGrid: {
            groupHeaderFormat: '{name}: <b>{value} </b>({count:n0} items)'
        },
        FlexGridFilter: {
            // filter
            ascending: '\u2191 Ascending',
            descending: '\u2193 Descending',
            apply: 'Apply',
            clear: 'Clear',
            conditions: 'Filter by Condition',
            values: 'Filter by Value',
            // value filter
            search: 'Search',
            selectAll: 'Select All',
            null: '(nothing)',
            // condition filter
            header: 'Show items where the value',
            and: 'And',
            or: 'Or',
            stringOperators: [
                { name: '(not set)', op: null },
                { name: 'Equals', op: 0 },
                { name: 'Does not equal', op: 1 },
                { name: 'Begins with', op: 6 },
                { name: 'Ends with', op: 7 },
                { name: 'Contains', op: 8 },
                { name: 'Does not contain', op: 9 }
            ],
            numberOperators: [
                { name: '(not set)', op: null },
                { name: 'Equals', op: 0 },
                { name: 'Does not equal', op: 1 },
                { name: 'Is Greater than', op: 2 },
                { name: 'Is Greater than or equal to', op: 3 },
                { name: 'Is Less than', op: 4 },
                { name: 'Is Less than or equal to', op: 5 }
            ],
            dateOperators: [
                { name: '(not set)', op: null },
                { name: 'Equals', op: 0 },
                { name: 'Is Before', op: 4 },
                { name: 'Is After', op: 3 }
            ],
            booleanOperators: [
                { name: '(not set)', op: null },
                { name: 'Equals', op: 0 },
                { name: 'Does not equal', op: 1 }
            ]
        }
    };
})(wijmo || (wijmo = {}));
;
//# sourceMappingURL=wijmo.culture.en-CA.js.map
