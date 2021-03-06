/*
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
var __extends, wijmo;
(function(n) {
    'use strict';

    function st() {
        return ot
    }

    function a(n, t) {
        return n == null ? null : f(t) ? h(n.implementsInterface) && n.implementsInterface(t) ? n : null : n instanceof t ? n : null
    }

    function ct(n) {
        return f(n) || e(n) || l(n) || u(n)
    }

    function f(n) {
        return typeof n == 'string'
    }

    function lt(n) {
        return n == null ? !0 : n.replace(/\s/g, '').length < 1
    }

    function e(n) {
        return typeof n == 'number'
    }

    function b(n) {
        return e(n) && n == Math.round(n)
    }

    function l(n) {
        return typeof n == 'boolean'
    }

    function h(n) {
        return typeof n == 'function'
    }

    function at(n) {
        return typeof n == 'undefined'
    }

    function u(n) {
        return n instanceof Date && !isNaN(n.getTime())
    }

    function o(n) {
        return n instanceof Array
    }

    function v(n) {
        return n != null && typeof n == 'object' && !u(n) && !o(n)
    }

    function vt(n) {
        if (n instanceof c) return n;
        if (n.touches && n.touches.length > 0 && (n = n.touches[0]), e(n.clientX) && e(n.clientY)) return new c(n.clientX + pageXOffset, n.clientY + pageYOffset);
        throw 'Mouse or touch event expected.';
    }

    function yt(n) {
        return e(n) ? r.Number : l(n) ? r.Boolean : u(n) ? r.Date : f(n) ? r.String : o(n) ? r.Array : r.Object
    }

    function pt(t, i, u) {
        var o, e;
        if (t != null) {
            if (f(t)) switch (i) {
                case r.Number:
                    return o = n.Globalize.parseFloat(t, u), isNaN(o) ? t : o;
                case r.Date:
                    return e = n.Globalize.parseDate(t, u), e || u || !t || (e = new Date(t)), e && isFinite(e.getTime()) ? e : t;
                case r.Boolean:
                    switch (t.toLowerCase()) {
                        case 'true':
                            return !0;
                        case 'false':
                            return !1
                    }
                    return t
            }
            if (i == r.String) return n.Globalize.format(t, u)
        }
        return t
    }

    function wt(n, t, i) {
        var u, r;
        return i ? (r = n.toString(), u = r.indexOf('.'), u > -1 && (r = r.substr(0, u + 1 + t), n = parseFloat(r))) : (r = n.toFixed(t), n = parseFloat(r)), n
    }

    function bt(t, i, r) {
        return t = y(t), t.replace(/\{(.*?)(:(.*?))?\}/g, function(t, u, f, e) {
            var o = t;
            return u && u[0] != '{' && i && (o = i[u], e && (o = n.Globalize.format(o, e)), r && (o = r(i, u, e, o))), o == null ? '' : o
        })
    }

    function kt(n, t, i) {
        return n != null && (i != null && n > i && (n = i), t != null && n < t && (n = t)), n
    }

    function k(i, r) {
        var u, f;
        if (r)
            for (u in r) u[0] != '_' && (t(u in i, 'Unknown key "' + u + '".'), f = r[u], i._copy && i._copy(u, f) || (i[u] instanceof n.Event && h(f) ? i[u].addHandler(f) : v(f) && i[u] && u != 'itemsSource' ? k(i[u], f) : i[u] = f))
    }

    function t(n, t) {
        if (!n) throw '** Assertion failed in Wijmo: ' + t;
    }

    function y(n, i) {
        return i === void 0 && (i = !0), t(i && n == null || f(n), 'String expected.'), n
    }

    function i(n, i, r) {
        if (i === void 0 && (i = !1), r === void 0 && (r = !1), t(i && n == null || e(n), 'Number expected.'), r && n && n < 0) throw 'Positive number expected.';
        return n
    }

    function dt(n, i, r) {
        if (i === void 0 && (i = !1), r === void 0 && (r = !1), t(i && n == null || b(n), 'Integer expected.'), r && n && n < 0) throw 'Positive integer expected.';
        return n
    }

    function d(n, i) {
        return i === void 0 && (i = !1), t(i && n == null || l(n), 'Boolean expected.'), n
    }

    function gt(n, i) {
        return i === void 0 && (i = !1), t(i && n == null || u(n), 'Date expected.'), n
    }

    function s(n, i) {
        return i === void 0 && (i = !0), t(i && n == null || h(n), 'Function expected.'), n
    }

    function ni(n, i) {
        return i === void 0 && (i = !0), t(i && n == null || o(n), 'Array expected.'), n
    }

    function g(n, i, r) {
        return r === void 0 && (r = !1), n = a(n, i), t(r || n != null, i + ' expected.'), n
    }

    function ti(n, i, r) {
        if (r === void 0 && (r = !1), n == null && r) return null;
        var u = i[n];
        return t(u != null, 'Invalid enum value.'), e(u) ? u : n
    }

    function ii(i, r) {
        if (r === void 0 && (r = !0), i == null && r) return null;
        var u = a(i, 'ICollectionView');
        return u != null ? u : (o(i) || t(!1, 'Array or ICollectionView expected.'), new n.collections.CollectionView(i))
    }

    function ri(n) {
        return n && n.items && n.items.length
    }

    function ui(n) {
        return n && n.length ? n[0].toUpperCase() + n.substr(1).replace(/([a-z])([A-Z])/g, '$1 $2') : ''
    }

    function fi(n) {
        return f(n) && (n = n.replace(/[&<>"'\/]/g, function(n) {
            return nt[n]
        })), n
    }

    function p(n, t) {
        if (n && n.getAttribute) {
            var i = new RegExp('\\b' + t + '\\b');
            return n && i.test(n.getAttribute('class'))
        }
        return !1
    }

    function tt(n, t) {
        if (n && t && n.setAttribute && p(n, t)) {
            var i = new RegExp('\\s?\\b' + t + '\\b', 'g'),
                r = n.getAttribute('class');
            n.setAttribute('class', r.replace(i, ''))
        }
    }

    function it(n, t) {
        if (n && t && n.setAttribute && !p(n, t)) {
            var i = n.getAttribute('class');
            n.setAttribute('class', i ? i + ' ' + t : t)
        }
    }

    function rt(n, t, i) {
        i ? it(n, t) : tt(n, t)
    }

    function ei(n, t, r) {
        if (r === void 0 && (r = t), n = g(n, HTMLInputElement), ut(document.body, n) && !n.disabled && n.style.display != 'none') try {
            n.focus();
            n.setSelectionRange(i(t), i(r))
        } catch (u) {}
    }

    function oi(n) {
        return n instanceof Element ? n : f(n) ? document.querySelector(n) : n && n.jquery ? n[0] : null
    }

    function si(n, t) {
        var i = document.createElement('div'),
            r;
        return i.innerHTML = n, r = i.removeChild(i.firstChild), t instanceof HTMLElement ? t.appendChild(r) : r
    }

    function hi(n, t) {
        if (t == null) {
            n.hasChildNodes() && (n.textContent = '');
            return
        }
        var i = n.firstChild;
        n.childNodes.length == 1 && i.nodeType == 3 ? i.nodeValue != t && (i.nodeValue = t) : (i || t) && (n.textContent = t)
    }

    function ut(n, t) {
        for (var i = t; i; i = i.parentNode)
            if (i === n) return !0;
        return !1
    }

    function ci(n, t) {
        var i = n ? n.matches || n.webkitMatchesSelector || n.mozMatchesSelector || n.msMatchesSelector : null;
        if (i)
            for (; n; n = n.parentNode)
                if (n instanceof Element && i.call(n, t)) return n;
        return null
    }

    function li(n, t) {
        t ? n.removeAttribute('disabled') : n.setAttribute('disabled', 'true');
        rt(n, 'wj-state-disabled', !t)
    }

    function ai(n) {
        var t = n.getBoundingClientRect();
        return new w(t.left + window.pageXOffset, t.top + window.pageYOffset, t.width, t.height)
    }

    function vi(n, t) {
        var u = n.style,
            i, r;
        for (i in t) r = t[i], typeof r == 'number' && i.match(/width|height|left|top|right|bottom|size|padding|margin'/i) && (r += 'px'), u[i] != r && (u[i] = r.toString())
    }

    function yi(n, t, r) {
        t === void 0 && (t = 400);
        r === void 0 && (r = 35);
        s(n);
        i(t, !1, !0);
        i(r, !1, !0);
        var f = Date.now(),
            u = setInterval(function() {
                var i = Math.min(1, (Date.now() - f) / t);
                i = Math.sin(i * Math.PI / 2);
                i *= i;
                requestAnimationFrame(function() {
                    n(i)
                });
                i >= 1 && clearInterval(u)
            }, r);
        return u
    }

    function pi(n, t) {
        var u, e, a, i, o, c;
        t || (t = {});
        var l = t.method ? y(t.method).toUpperCase() : 'GET',
            p = t.async != null ? d(t.async) : !0,
            r = t.data;
        if (r != null && l == 'GET') {
            u = [];
            for (e in r) u.push(e + '=' + r[e]);
            u.length && (a = n.indexOf('?') < 0 ? '?' : '&', n += a + u.join('&'));
            r = null
        }
        if (i = new XMLHttpRequest, i.URL_DEBUG = n, o = !1, r == null || f(r) || (o = v(r), r = JSON.stringify(r)), i.onload = function() {
                i.readyState == 4 && (i.status < 300 ? t.success && s(t.success)(i) : t.error && s(t.error)(i), t.complete && s(t.complete)(i))
            }, i.onerror = function() {
                if (t.error) s(t.error)(i);
                else throw 'HttpRequest Error: ' + i.status + ' ' + i.statusText;
            }, i.open(l, n, p, t.user, t.password), t.user && t.password && i.setRequestHeader('Authorization', 'Basic ' + btoa(t.user + ':' + t.password)), o && i.setRequestHeader('Content-Type', 'application/json'), t.requestHeaders)
            for (c in t.requestHeaders) i.setRequestHeader(c, t.requestHeaders[c]);
        return h(t.beforeSend) && t.beforeSend(i), i.send(r), i
    }
    var ot = '5.20161.138',
        ht, r, nt, c, ft, w, et;
    n.getVersion = st,
        function(n) {
            n[n.Back = 8] = "Back";
            n[n.Tab = 9] = "Tab";
            n[n.Enter = 13] = "Enter";
            n[n.Escape = 27] = "Escape";
            n[n.Space = 32] = "Space";
            n[n.PageUp = 33] = "PageUp";
            n[n.PageDown = 34] = "PageDown";
            n[n.End = 35] = "End";
            n[n.Home = 36] = "Home";
            n[n.Left = 37] = "Left";
            n[n.Up = 38] = "Up";
            n[n.Right = 39] = "Right";
            n[n.Down = 40] = "Down";
            n[n.Delete = 46] = "Delete";
            n[n.F1 = 112] = "F1";
            n[n.F2 = 113] = "F2";
            n[n.F3 = 114] = "F3";
            n[n.F4 = 115] = "F4";
            n[n.F5 = 116] = "F5";
            n[n.F6 = 117] = "F6";
            n[n.F7 = 118] = "F7";
            n[n.F8 = 119] = "F8";
            n[n.F9 = 120] = "F9";
            n[n.F10 = 121] = "F10";
            n[n.F11 = 122] = "F11";
            n[n.F12 = 123] = "F12"
        }(n.Key || (n.Key = {}));
    ht = n.Key,
        function(n) {
            n[n.Object = 0] = "Object";
            n[n.String = 1] = "String";
            n[n.Number = 2] = "Number";
            n[n.Boolean = 3] = "Boolean";
            n[n.Date = 4] = "Date";
            n[n.Array = 5] = "Array"
        }(n.DataType || (n.DataType = {}));
    r = n.DataType;
    n.tryCast = a;
    n.isPrimitive = ct;
    n.isString = f;
    n.isNullOrWhiteSpace = lt;
    n.isNumber = e;
    n.isInt = b;
    n.isBoolean = l;
    n.isFunction = h;
    n.isUndefined = at;
    n.isDate = u;
    n.isArray = o;
    n.isObject = v;
    n.mouseToPage = vt;
    n.getType = yt;
    n.changeType = pt;
    n.toFixed = wt;
    n.format = bt;
    n.clamp = kt;
    n.copy = k;
    n.assert = t;
    n.asString = y;
    n.asNumber = i;
    n.asInt = dt;
    n.asBoolean = d;
    n.asDate = gt;
    n.asFunction = s;
    n.asArray = ni;
    n.asType = g;
    n.asEnum = ti;
    n.asCollectionView = ii;
    n.hasItems = ri;
    n.toHeaderCase = ui;
    n.escapeHtml = fi;
    nt = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#39;',
        '/': '&#x2F;'
    };
    n.hasClass = p;
    n.removeClass = tt;
    n.addClass = it;
    n.toggleClass = rt;
    n.setSelectionRange = ei;
    n.getElement = oi;
    n.createElement = si;
    n.setText = hi;
    n.contains = ut;
    n.closest = ci;
    n.enable = li;
    n.getElementRect = ai;
    n.setCss = vi;
    n.animate = yi;
    c = function() {
        function n(n, t) {
            n === void 0 && (n = 0);
            t === void 0 && (t = 0);
            this.x = i(n);
            this.y = i(t)
        }
        return n.prototype.equals = function(t) {
            return t instanceof n && this.x == t.x && this.y == t.y
        }, n.prototype.clone = function() {
            return new n(this.x, this.y)
        }, n
    }();
    n.Point = c;
    ft = function() {
        function n(n, t) {
            n === void 0 && (n = 0);
            t === void 0 && (t = 0);
            this.width = i(n);
            this.height = i(t)
        }
        return n.prototype.equals = function(t) {
            return t instanceof n && this.width == t.width && this.height == t.height
        }, n.prototype.clone = function() {
            return new n(this.width, this.height)
        }, n
    }();
    n.Size = ft;
    w = function() {
        function n(n, t, r, u) {
            this.left = i(n);
            this.top = i(t);
            this.width = i(r);
            this.height = i(u)
        }
        return Object.defineProperty(n.prototype, "right", {
            get: function() {
                return this.left + this.width
            },
            enumerable: !0,
            configurable: !0
        }), Object.defineProperty(n.prototype, "bottom", {
            get: function() {
                return this.top + this.height
            },
            enumerable: !0,
            configurable: !0
        }), n.prototype.clone = function() {
            return new n(this.left, this.top, this.width, this.height)
        }, n.fromBoundingRect = function(i) {
            if (i.left != null) return new n(i.left, i.top, i.width, i.height);
            if (i.x != null) return new n(i.x, i.y, i.width, i.height);
            t(!1, 'Invalid source rectangle.')
        }, n.union = function(t, i) {
            var r = Math.min(t.left, i.left),
                u = Math.min(t.top, i.top),
                f = Math.max(t.right, i.right),
                e = Math.max(t.bottom, i.bottom);
            return new n(r, u, f - r, e - u)
        }, n.prototype.contains = function(i) {
            if (i instanceof c) return i.x >= this.left && i.x <= this.right && i.y >= this.top && i.y <= this.bottom;
            if (i instanceof n) {
                var r = i;
                return r.left >= this.left && r.right <= this.right && r.top >= this.top && r.bottom <= this.bottom
            }
            t(!1, 'Point or Rect expected.')
        }, n.prototype.inflate = function(t, i) {
            return new n(this.left - t, this.top - i, this.width + 2 * t, this.height + 2 * i)
        }, n
    }();
    n.Rect = w;
    et = function() {
        function t() {}
        return t.addDays = function(n, t) {
            return new Date(n.getFullYear(), n.getMonth(), n.getDate() + t)
        }, t.addMonths = function(n, t) {
            return new Date(n.getFullYear(), n.getMonth() + t, n.getDate())
        }, t.addYears = function(n, t) {
            return new Date(n.getFullYear() + t, n.getMonth(), n.getDate())
        }, t.addHours = function(n, t) {
            return new Date(n.getFullYear(), n.getMonth(), n.getDate(), n.getHours() + t)
        }, t.addMinutes = function(n, t) {
            return new Date(n.getFullYear(), n.getMonth(), n.getDate(), n.getHours(), n.getMinutes() + t)
        }, t.addSeconds = function(n, t) {
            return new Date(n.getFullYear(), n.getMonth(), n.getDate(), n.getHours(), n.getMinutes(), n.getSeconds() + t)
        }, t.sameDate = function(n, t) {
            return u(n) && u(t) && n.getFullYear() == t.getFullYear() && n.getMonth() == t.getMonth() && n.getDate() == t.getDate()
        }, t.sameTime = function(n, t) {
            return u(n) && u(t) && n.getHours() == t.getHours() && n.getMinutes() == t.getMinutes() && n.getSeconds() == t.getSeconds()
        }, t.equals = function(n, t) {
            return u(n) && u(t) && n.getTime() == t.getTime()
        }, t.fromDateTime = function(n, t) {
            return !n && !t ? null : (n || (n = t), t || (t = n), new Date(n.getFullYear(), n.getMonth(), n.getDate(), t.getHours(), t.getMinutes(), t.getSeconds()))
        }, t.toFiscal = function(i, r) {
            var u = n.culture.Globalize.calendar;
            return o(u.fiscalYearOffsets) ? t.addMonths(i, -u.fiscalYearOffsets[r ? 0 : 1]) : i
        }, t.fromFiscal = function(i, r) {
            var u = n.culture.Globalize.calendar;
            return o(u.fiscalYearOffsets) ? t.addMonths(i, +u.fiscalYearOffsets[r ? 0 : 1]) : i
        }, t.clone = function(n) {
            return t.fromDateTime(n, n)
        }, t
    }();
    n.DateTime = et;
    n.httpRequest = pi
})(wijmo || (wijmo = {})),
function(n) {
    'use strict';
    n.culture = {
        Globalize: {
            numberFormat: {
                '.': '.',
                ',': ',',
                percent: {
                    pattern: ['-n %', 'n %']
                },
                currency: {
                    decimals: 2,
                    symbol: '$',
                    pattern: ['($n)', '$n']
                }
            },
            calendar: {
                '/': '/',
                ':': ':',
                firstDay: 0,
                days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
                daysAbbr: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                monthsAbbr: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                am: ['AM', 'A'],
                pm: ['PM', 'P'],
                eras: ['A.D.', 'B.C.'],
                patterns: {
                    d: 'M/d/yyyy',
                    D: 'dddd, MMMM dd, yyyy',
                    f: 'dddd, MMMM dd, yyyy h:mm tt',
                    F: 'dddd, MMMM dd, yyyy h:mm:ss tt',
                    t: 'h:mm tt',
                    T: 'h:mm:ss tt',
                    M: 'MMMM d',
                    m: 'MMMM d',
                    Y: 'MMMM, yyyy',
                    y: 'MMMM, yyyy',
                    g: 'M/d/yyyy h:mm tt',
                    G: 'M/d/yyyy h:mm:ss tt',
                    s: 'yyyy"-"MM"-"dd"T"HH":"mm":"ss',
                    o: 'yyyy"-"MM"-"dd"T"HH":"mm":"ss"."fffffffK',
                    O: 'yyyy"-"MM"-"dd"T"HH":"mm":"ss"."fffffffK',
                    U: 'dddd, MMMM dd, yyyy h:mm:ss tt'
                },
                fiscalYearOffsets: [-3, -3]
            }
        }
    };
    var t = function() {
        function t() {}
        return t.format = function(i, r, u, f) {
            return n.isString(i) ? i : n.isNumber(i) ? (r = r || (i == Math.round(i) ? 'n0' : 'n2'), t.formatNumber(i, r, u, f)) : n.isDate(i) ? (r = r || 'd', t.formatDate(i, r)) : i != null ? i.toString() : ''
        }, t.formatNumber = function(t, i, r, u) {
            var c, y, l;
            n.asNumber(t);
            n.asString(i);
            var f, o = i ? i.match(/([a-z])(\d*)(,*)(.*)/i) : null,
                s = n.culture.Globalize.numberFormat,
                e = o ? o[1].toLowerCase() : 'n',
                h = o && o[2] ? parseInt(o[2]) : e == 'c' ? s.currency.decimals : t == Math.round(t) ? 0 : 2,
                p = o && o[3] ? 3 * o[3].length : 0,
                w = o && o[4] ? o[4] : s.currency.symbol,
                a = s['.'],
                v = s[','];
            if (p && (t /= Math.pow(10, p)), e == 'd' || e == 'x') {
                for (f = Math.round(Math.abs(t)).toString(e == 'd' ? 10 : 16); f.length < h;) f = '0' + f;
                return t < 0 && (f = '-' + f), i && i[0] == 'X' && (f = f.toUpperCase()), f
            }
            return e == 'p' && (t = n.toFixed(t * 100, h, !1)), u && (t = n.toFixed(t, h, !0)), f = e == 'c' || e == 'p' ? Math.abs(t).toFixed(h) : t.toFixed(h), (r || e == 'g') && f.indexOf('.') > -1 && (f = f.replace(/(\.[0-9]*?)0+$/g, '$1'), f = f.replace(/\.$/, '')), a != '.' && (f = f.replace('.', a)), v && (e == 'n' || e == 'c' || e == 'p') && (c = f.indexOf(a), y = /\B(?=(\d\d\d)+(?!\d))/g, f = c > -1 ? f.substr(0, c).replace(y, v) + f.substr(c) : f.replace(y, v)), e == 'c' && (l = s.currency.pattern[t < 0 ? 0 : 1], f = l.replace('n', f).replace('$', w)), e == 'p' && (l = s.percent.pattern[t < 0 ? 0 : 1], f = l.replace('n', f)), f
        }, t.formatDate = function(i, r) {
            var f, e, u;
            i = n.asDate(i);
            switch (r) {
                case 'r':
                case 'R':
                    return i.toUTCString();
                case 'u':
                    return i.toISOString().replace(/\.\d{3}/, '')
            }
            for (r = t._expandFormat(r), f = t._parseDateFormat(r), e = '', u = 0; u < f.length; u++) e += t._formatDatePart(i, r, f[u]);
            return e
        }, t.parseInt = function(n, i) {
            return Math.round(t.parseFloat(n, i))
        }, t.parseFloat = function(t, i) {
            var r = t.indexOf('-') > -1 || t.indexOf('(') > -1 && t.indexOf(')') > -1 ? -1 : 1,
                u = t.indexOf('%') > -1 ? .01 : 1,
                f = i ? i.match(/,+/) : null,
                e = f ? f[0].length * 3 : 0;
            if (i && (i[0] == 'x' || i[0] == 'X')) return t = t.replace(/[^0-9a-f]+.*$/gi, ''), parseInt(t, 16) * r * u * Math.pow(10, e);
            var o = n.culture.Globalize.numberFormat['.'],
                s = new RegExp('[^\\d\\' + o + ']', 'g'),
                h = t.replace(s, '').replace(o, '.');
            return parseFloat(h) * r * u * Math.pow(10, e)
        }, t.parseDate = function(i, r) {
            var ut, s, e, p, ht, nt;
            if (i = n.asString(i), !i) return null;
            if (r == 'u') return new Date(i);
            if (r == 'R' || r == 'r') return ut = /(([0-9]+)\-([0-9]+)\-([0-9]+))?\s?(([0-9]+):([0-9]+)(:([0-9]+))?)?/, s = i.match(ut), s[1] || s[5] ? (e = s[1] ? new Date(parseInt(s[2]), parseInt(s[3]) - 1, parseInt(s[4])) : new Date, s[5] && (e.setHours(parseInt(s[6])), e.setMinutes(parseInt(s[7])), e.setSeconds(s[8] ? parseInt(s[9]) : 0))) : e = new Date(i), isNaN(e.getTime()) ? null : e;
            r = t._expandFormat(r ? r : 'd');
            var h = n.culture.Globalize.calendar,
                ft = t._CJK,
                ct = new RegExp('(\\' + h['/'] + ')|(\\' + h[':'] + ")|(\\d+)|([" + ft + "\\.]{2,})|([" + ft + ']+)', 'gi'),
                c = i.match(ct),
                w = t._parseDateFormat(r),
                y = 0,
                l = -1,
                a = 0,
                d = 1,
                o = 0,
                b = 0,
                k = 0,
                et = 0,
                tt = -1,
                ot, it, st, g, rt;
            if (!c || !c.length || !w || !w.length) return null;
            for (p = 0; p < w.length && c; p++) {
                var v = p - y,
                    u = v > -1 && v < c.length ? c[v] : '',
                    f = w[p].length;
                switch (w[p]) {
                    case 'EEEE':
                    case 'EEE':
                    case 'EE':
                    case 'E':
                    case 'eeee':
                    case 'eee':
                    case 'ee':
                    case 'e':
                        rt = w[p];
                    case 'yyyy':
                    case 'yyy':
                    case 'yy':
                    case 'y':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        l = parseInt(u);
                        break;
                    case 'MMMM':
                    case 'MMM':
                        for (g = !0, ht = u.toLowerCase(), a = -1, nt = 0; nt < 12; nt++)
                            if (h.months[nt].toLowerCase().indexOf(ht) == 0) {
                                a = nt;
                                break
                            }
                        if (a > -1) break;
                    case 'MM':
                    case 'M':
                        g = !0;
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        a = parseInt(u) - 1;
                        break;
                    case 'dddd':
                    case 'ddd':
                        ot = !0;
                        break;
                    case 'dd':
                    case 'd':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        d = parseInt(u);
                        it = !0;
                        break;
                    case 'hh':
                    case 'h':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        o = parseInt(u);
                        o = o == 12 ? 0 : o;
                        break;
                    case 'HH':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        o = parseInt(u);
                        break;
                    case 'H':
                        o = parseInt(u);
                        break;
                    case 'mm':
                    case 'm':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        b = parseInt(u);
                        break;
                    case 'ss':
                    case 's':
                        f > 1 && u.length > f && (c[v] = u.substr(f), u = u.substr(0, f), y++);
                        k = parseInt(u);
                        break;
                    case 'fffffff':
                    case 'FFFFFFF':
                    case 'ffffff':
                    case 'FFFFFF':
                    case 'fffff':
                    case 'FFFFF':
                    case 'ffff':
                    case 'FFFF':
                    case 'fff':
                    case 'FFF':
                    case 'ff':
                    case 'FF':
                    case 'f':
                    case 'F':
                        et = parseInt(u) / Math.pow(10, f - 3);
                        break;
                    case 'tt':
                    case 't':
                        u = u.toUpperCase();
                        (h.pm[0] && u == h.pm[0] && o < 12 || h.pm[1] && u == h.pm[1] && o < 12) && (o += 12);
                        break;
                    case 'q':
                    case 'Q':
                    case 'u':
                    case 'U':
                        st = !0;
                        break;
                    case 'ggg':
                    case 'gg':
                    case 'g':
                        tt = h.eras.length > 1 ? t._getEra(u, h) : -1;
                        break;
                    case h['/']:
                    case h[':']:
                        if (u && u != w[p]) return null;
                        break;
                    case 'K':
                        break;
                    default:
                        t._unquote(w[p]) != u && y++
                }
            }
            if (g && it && (isNaN(o) && (o = 0), isNaN(b) && (b = 0), isNaN(k) && (k = 0)), a < 0 || a > 11 || isNaN(a) || d < 0 || d > 31 || isNaN(d) || o < 0 || o > 24 || isNaN(o) || b < 0 || b > 60 || isNaN(b) || k < 0 || k > 60 || isNaN(k)) return null;
            if (rt) {
                if (!g) return null;
                e = new Date(l, a);
                e = n.DateTime.fromFiscal(e, rt[0] == 'E');
                l = e.getFullYear();
                a = e.getMonth()
            }
            return ot && !it ? null : st && !g ? null : (l < 0 && (l = (new Date).getFullYear()), tt > -1 ? l = l + h.eras[tt].start.getFullYear() - 1 : l < 100 && (l += l >= 30 ? 1900 : 2e3), e = new Date(l, a, d, o, b + 0, k, et), isNaN(e.getTime()) ? null : e)
        }, t.getFirstDayOfWeek = function() {
            var t = n.culture.Globalize.calendar.firstDay;
            return t ? t : 0
        }, t.getNumberDecimalSeparator = function() {
            var t = n.culture.Globalize.numberFormat['.'];
            return t ? t : '.'
        }, t._unquote = function(n) {
            return n.length > 1 && n[0] == n[n.length - 1] && (n[0] == '\'' || n[0] == '\"') ? n.substr(1, n.length - 2) : n
        }, t._parseDateFormat = function(n) {
            var u, e, i, r, f;
            if (n in t._dateFomatParts) return t._dateFomatParts[n];
            for (u = [], e = '', i = 0; i > -1 && i < n.length; i++) {
                if (f = n[i], (f == '\'' || f == '"') && (r = n.indexOf(f, i + 1), r > -1)) {
                    u.push(n.substring(i, r + 1));
                    i = r;
                    continue
                }
                for (r = i + 1; r < n.length; r++)
                    if (n[r] != f) break;
                u.push(n.substring(i, r));
                i = r - 1
            }
            return t._dateFomatParts[n] = u, u
        }, t._formatDatePart = function(i, r, u) {
            var f = n.culture.Globalize.calendar,
                o = 0,
                h = 0,
                l = 0,
                c, e = u.length,
                s;
            switch (u) {
                case 'yyyy':
                case 'yyy':
                case 'yy':
                case 'y':
                case 'EEEE':
                case 'EEE':
                case 'EE':
                case 'E':
                case 'eeee':
                case 'eee':
                case 'ee':
                case 'e':
                    return c = u[0] == 'E' ? n.DateTime.toFiscal(i, !0) : u[0] == 'e' ? n.DateTime.toFiscal(i, !1) : i, h = c.getFullYear(), f.eras.length > 1 && r.indexOf('g') > -1 && (o = t._getEra(i, f), o > -1 && (h = h - f.eras[o].start.getFullYear() + 1)), t._zeroPad(h, 4).substr(4 - u.length);
                case 'MMMM':
                    return f.months[i.getMonth()];
                case 'MMM':
                    return f.monthsAbbr[i.getMonth()];
                case 'MM':
                case 'M':
                    return t._zeroPad(i.getMonth() + 1, e);
                case 'dddd':
                    return f.days[i.getDay()];
                case 'ddd':
                    return f.daysAbbr[i.getDay()];
                case 'dd':
                    return t._zeroPad(i.getDate(), 2);
                case 'd':
                    return i.getDate().toString();
                case 'hh':
                case 'h':
                    return t._zeroPad(t._h12(i), e);
                case 'HH':
                case 'H':
                    return t._zeroPad(i.getHours(), e);
                case 'mm':
                case 'm':
                    return t._zeroPad(i.getMinutes(), e);
                case 'ss':
                case 's':
                    return t._zeroPad(i.getSeconds(), e);
                case 'fffffff':
                case 'FFFFFFF':
                case 'ffffff':
                case 'FFFFFF':
                case 'fffff':
                case 'FFFFF':
                case 'ffff':
                case 'FFFF':
                case 'fff':
                case 'FFF':
                case 'ff':
                case 'FF':
                case 'f':
                case 'F':
                    return l = i.getMilliseconds() * Math.pow(10, e - 3), u[0] == 'f' ? t._zeroPad(l, e) : l.toFixed(0);
                case 'tt':
                    return i.getHours() < 12 ? f.am[0] : f.pm[0];
                case 't':
                    return i.getHours() < 12 ? f.am[1] : f.pm[1];
                case 'q':
                case 'Q':
                    return (Math.floor(i.getMonth() / 3) + 1).toString();
                case 'u':
                case 'U':
                    return c = n.DateTime.toFiscal(i, u == 'U'), (Math.floor(c.getMonth() / 3) + 1).toString();
                case 'ggg':
                case 'gg':
                case 'g':
                    return f.eras.length > 1 && (o = t._getEra(i, f), o > -1) ? u == 'ggg' ? f.eras[o].name : u == 'gg' ? f.eras[o].name[0] : f.eras[o].symbol : f.eras[0];
                case ':':
                case '/':
                    return f[u];
                case 'K':
                    return s = i.toString().match(/(\+|\-)(\d{2})(\d{2})/), s ? s[1] + s[2] + s[3] : ''
            }
            return e > 1 && u[0] == u[e - 1] && (u[0] == '\"' || u[0] == '\'') ? u.substr(1, e - 2) : u
        }, t._getEra = function(t, i) {
            var r;
            if (n.isDate(t)) {
                for (r = 0; r < i.eras.length; r++)
                    if (t >= i.eras[r].start) return r
            } else if (n.isString(t))
                for (r = 0; r < i.eras.length; r++)
                    if (i.eras[r].name && (i.eras[r].name.indexOf(t) == 0 || i.eras[r].symbol.indexOf(t) == 0)) return r;
            return -1
        }, t._expandFormat = function(t) {
            var i = n.culture.Globalize.calendar.patterns[t];
            return i ? i : t
        }, t._zeroPad = function(n, t) {
            var i = n.toFixed(0),
                r = t - i.length + 1;
            return r > 0 ? Array(r).join('0') + i : i
        }, t._h12 = function(t) {
            var r = n.culture.Globalize.calendar,
                i = t.getHours();
            return r.am && r.am[0] && (i = i % 12, i == 0 && (i = 12)), i
        }, t._CJK = 'a-zu00C0-u017Fu3000-u30ffu4e00-u9faf'.replace(/u/g, '\\u'), t._dateFomatParts = {}, t
    }();
    n.Globalize = t
}(wijmo || (wijmo = {})),
function(n) {
    'use strict';
    var t = function() {
        function n(n) {
            this.path = n
        }
        return Object.defineProperty(n.prototype, "path", {
            get: function() {
                return this._path
            },
            set: function(n) {
                var t, i, r;
                for (this._path = n, this._parts = n ? n.split('.') : [], t = 0; t < this._parts.length; t++) i = this._parts[t], r = i.indexOf('['), r > -1 && (this._parts[t] = i.substr(0, r), this._parts.splice(++t, 0, parseInt(i.substr(r + 1))));
                this._key = this._parts.length == 1 ? this._parts[0] : null
            },
            enumerable: !0,
            configurable: !0
        }), n.prototype.getValue = function(n) {
            if (n) {
                if (this._key) return n[this._key];
                if (this._path in n) return n[this._path];
                for (var t = 0; t < this._parts.length && n; t++) n = n[this._parts[t]]
            }
            return n
        }, n.prototype.setValue = function(n, t) {
            if (n) {
                if (this._path in n) {
                    n[this._path] = t;
                    return
                }
                for (var i = 0; i < this._parts.length - 1; i++)
                    if (n = n[this._parts[i]], n == null) return;
                n[this._parts[this._parts.length - 1]] = t
            }
        }, n
    }();
    n.Binding = t
}(wijmo || (wijmo = {}));
__extends = this && this.__extends || function(n, t) {
        function r() {
            this.constructor = n
        }
        for (var i in t) t.hasOwnProperty(i) && (n[i] = t[i]);
        n.prototype = t === null ? Object.create(t) : (r.prototype = t.prototype, new r)
    },
    function(n) {
        'use strict';
        var f = function() {
                function n(n, t) {
                    this.handler = n;
                    this.self = t
                }
                return n
            }(),
            e = function() {
                function i() {
                    this._handlers = []
                }
                return i.prototype.addHandler = function(t, i) {
                    n.asFunction(t);
                    this._handlers.push(new f(t, i))
                }, i.prototype.removeHandler = function(t, i) {
                    var r, u;
                    for (n.asFunction(t), r = 0; r < this._handlers.length; r++)
                        if (u = this._handlers[r], (u.handler == t || t == null) && (u.self == i || i == null) && (this._handlers.splice(r, 1), t && i)) break
                }, i.prototype.removeAllHandlers = function() {
                    this._handlers.length = 0
                }, i.prototype.raise = function(n, i) {
                    var r, u;
                    for (i === void 0 && (i = t.empty), r = 0; r < this._handlers.length; r++) u = this._handlers[r], u.handler.call(u.self, n, i)
                }, Object.defineProperty(i.prototype, "hasHandlers", {
                    get: function() {
                        return this._handlers.length > 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), i
            }(),
            t, i, r, u;
        n.Event = e;
        t = function() {
            function n() {}
            return n.empty = new n, n
        }();
        n.EventArgs = t;
        i = function(n) {
            function t() {
                n.apply(this, arguments);
                this.cancel = !1
            }
            return __extends(t, n), t
        }(t);
        n.CancelEventArgs = i;
        r = function(n) {
            function t(t, i, r) {
                n.call(this);
                this._name = t;
                this._oldVal = i;
                this._newVal = r
            }
            return __extends(t, n), Object.defineProperty(t.prototype, "propertyName", {
                get: function() {
                    return this._name
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "oldValue", {
                get: function() {
                    return this._oldVal
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "newValue", {
                get: function() {
                    return this._newVal
                },
                enumerable: !0,
                configurable: !0
            }), t
        }(t);
        n.PropertyChangedEventArgs = r;
        u = function(n) {
            function t(t) {
                n.call(this);
                this._xhr = t
            }
            return __extends(t, n), Object.defineProperty(t.prototype, "request", {
                get: function() {
                    return this._xhr
                },
                enumerable: !0,
                configurable: !0
            }), t
        }(i);
        n.RequestErrorEventArgs = u
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';
        var t = function() {
            function t(i, r, u) {
                var c = this,
                    f, l, s, v, o;
                if (r === void 0 && (r = null), u === void 0 && (u = !1), this._focus = !1, this._updating = 0, this._fullUpdate = !1, this.gotFocus = new n.Event, this.lostFocus = new n.Event, n.assert(t.getControl(i) == null, 'Element is already hosting a control.'), f = n.getElement(i), n.assert(f != null, 'Cannot find the host element.'), this._orgOuter = f.outerHTML, this._orgInner = f.innerHTML, (f.tagName == 'INPUT' || f.tagName == 'SELECT') && (this._orgAtts = f.attributes, this._orgTag = f.tagName, f = this._replaceWithDiv(f)), this._e = f, f[t._DATA_KEY] = this, u == !0 && (this._szCtl = new n.Size(f.offsetWidth, f.offsetHeight), l = this._handleResize.bind(this), this.addEventListener(window, 'resize', l)), this.addEventListener(f, 'focus', function() {
                        setTimeout(function() {
                            c.containsFocus() && c._updateFocusState()
                        })
                    }, !0), this.addEventListener(f, 'blur', function() {
                        setTimeout(function() {
                            c.containsFocus() || c._updateFocusState()
                        })
                    }, !0), s = this._handleDisabled.bind(this), this.addEventListener(f, 'mousedown', s, !0), this.addEventListener(f, 'mouseup', s, !0), this.addEventListener(f, 'click', s, !0), this.addEventListener(f, 'dblclick', s, !0), this.addEventListener(f, 'keydown', s, !0), this.addEventListener(f, 'onmousewheel' in document ? 'mousewheel' : 'DOMMouseScroll', s, !0), t._touching == null && (t._touching = !1, 'ontouchstart' in window || 'onpointerdown' in window)) {
                    var e = document.body,
                        a = this._handleTouchStart,
                        h = this._handleTouchEnd;
                    'ontouchstart' in window ? (e.addEventListener('touchstart', a), e.addEventListener('touchend', h), e.addEventListener('touchcancel', h), e.addEventListener('touchleave', h)) : 'onpointerdown' in window && (e.addEventListener('pointerdown', a), e.addEventListener('pointerup', h), e.addEventListener('pointerout', h), e.addEventListener('pointercancel', h), e.addEventListener('pointerleave', h))
                }
                if (!t._wme || !t._wme.parentElement) {
                    v = 'Wijmo ' + n.getVersion() + ' eval';
                    t._wme = n.createElement('<div><a href="http://wijmo.com/products/wijmo-5/eval/">' + v + '</a></div>');
                    o = {
                        position: 'fixed',
                        padding: 5,
                        margin: 5,
                        background: '#fff',
                        boxShadow: '0 0 10px rgba(0, 0, 0, 0.25)',
                        fontSize: '11pt',
                        zIndex: 1e3,
                        opacity: .8,
                        display: 'block',
                        visibility: 'visible',
                        height: 'auto',
                        width: 'auto',
                        transform: 'none'
                    };
                    switch (Math.round(Math.random() * 100) % 2) {
                        case 0:
                            o.right = o.bottom = 0;
                            break;
                        case 1:
                            o.left = o.bottom = 0;
                            break;
                        case 2:
                            o.right = o.top = 0
                    }
                    n.setCss(t._wme, o);
                    document.body.appendChild(t._wme)
                }
            }
            return t.prototype.getTemplate = function() {
                for (var t, n = Object.getPrototypeOf(this); n; n = Object.getPrototypeOf(n))
                    if (t = n.constructor.controlTemplate, t) return t;
                return null
            }, t.prototype.applyTemplate = function(t, i, r, u) {
                var f = this._e,
                    s, e, h, o;
                if (t && n.addClass(f, t), s = null, i && (s = n.createElement(i, f)), f && !f.getAttribute('tabindex') && (f.tabIndex = f.querySelector('input') ? -1 : 0), r)
                    for (e in r) {
                        if (h = r[e], this[e] = s.querySelector('[wj-part="' + h + '"]'), this[e] == null && s.getAttribute('wj-part') == h && (this[e] = s), this[e] == null) throw 'Missing template part: "' + h + '"';
                        h == u && (o = f.attributes.name, o && o.value && this[e].setAttribute('name', o.value), o = f.attributes.accesskey, o && o.value && (this[e].setAttribute('accesskey', o.value), f.removeAttribute('accesskey')))
                    }
                return s
            }, t.prototype.dispose = function() {
                for (var e, u, i, r, o = this._e.querySelectorAll('.wj-control'), f = 0; f < o.length; f++) e = t.getControl(o[f]), e && e.dispose();
                this._toInv && clearTimeout(this._toInv);
                this.removeEventListener();
                for (i in this) i.length > 2 && i.indexOf('on') == 0 && (r = this[i[2].toLowerCase() + i.substr(3)], r instanceof n.Event && r.removeAllHandlers());
                if (u = this.collectionView, u instanceof n.collections.CollectionView)
                    for (i in u) r = u[i], r instanceof n.Event && r.removeHandler(null, this);
                this._e.parentNode && (this._e.outerHTML = this._orgOuter);
                this._e[t._DATA_KEY] = null;
                this._e = this._orgOuter = this._orgInner = this._orgAtts = this._orgTag = null
            }, t.getControl = function(i) {
                var r = n.getElement(i);
                return r ? n.asType(r[t._DATA_KEY], t, !0) : null
            }, Object.defineProperty(t.prototype, "hostElement", {
                get: function() {
                    return this._e
                },
                enumerable: !0,
                configurable: !0
            }), t.prototype.focus = function() {
                this._e.focus()
            }, t.prototype.containsFocus = function() {
                var f, i, r, e, u;
                if (!this._e) return !1;
                for (f = this._e.getElementsByClassName('wj-control'), i = 0; i < f.length; i++)
                    if (r = t.getControl(f[i]), r && r != this && r.containsFocus()) return !0;
                return (e = document.activeElement, u = t.getControl(n.closest(e, '.wj-control.wj-popup')), u && u.owner && n.contains(this._e, u.owner)) ? !0 : n.contains(this._e, e)
            }, t.prototype.invalidate = function(n) {
                var i = this;
                n === void 0 && (n = !0);
                this._fullUpdate = this._fullUpdate || n;
                this._toInv && (clearTimeout(this._toInv), this._toInv = null);
                this.isUpdating || (this._toInv = setTimeout(function() {
                    i.refresh(i._fullUpdate)
                }, t._REFRESH_INTERVAL))
            }, t.prototype.refresh = function(n) {
                n === void 0 && (n = !0);
                !this.isUpdating && this._toInv && (clearTimeout(this._toInv), this._toInv = null, this._fullUpdate = !1)
            }, t.invalidateAll = function(n) {
                var r, i;
                if (n || (n = document.body), r = t.getControl(n), r && r.invalidate(), n.children)
                    for (i = 0; i < n.children.length; i++) t.invalidateAll(n.children[i])
            }, t.refreshAll = function(n) {
                var r, i;
                if (n || (n = document.body), r = t.getControl(n), r && r.refresh(), n.children)
                    for (i = 0; i < n.children.length; i++) t.refreshAll(n.children[i])
            }, t.disposeAll = function(n) {
                var r = t.getControl(n),
                    i;
                if (r) r.dispose();
                else if (n.children)
                    for (i = 0; i < n.children.length; i++) t.disposeAll(n.children[i])
            }, t.prototype.beginUpdate = function() {
                this._updating++
            }, t.prototype.endUpdate = function() {
                this._updating--;
                this._updating <= 0 && this.invalidate()
            }, Object.defineProperty(t.prototype, "isUpdating", {
                get: function() {
                    return this._updating > 0
                },
                enumerable: !0,
                configurable: !0
            }), t.prototype.deferUpdate = function(n) {
                try {
                    this.beginUpdate();
                    n()
                } finally {
                    this.endUpdate()
                }
            }, Object.defineProperty(t.prototype, "isTouching", {
                get: function() {
                    return t._touching
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "disabled", {
                get: function() {
                    return this._e && this._e.getAttribute('disabled') != null
                },
                set: function(t) {
                    t = n.asBoolean(t, !0);
                    t != this.disabled && n.enable(this._e, !t)
                },
                enumerable: !0,
                configurable: !0
            }), t.prototype.initialize = function(t) {
                t && (this.beginUpdate(), n.copy(this, t), this.endUpdate())
            }, t.prototype.addEventListener = function(n, t, i, r) {
                r === void 0 && (r = !1);
                n && (n.addEventListener(t, i, r), this._listeners == null && (this._listeners = []), this._listeners.push({
                    target: n,
                    type: t,
                    fn: i,
                    capture: r
                }))
            }, t.prototype.removeEventListener = function(n, t, i) {
                var f = 0,
                    u, r;
                if (this._listeners)
                    for (u = 0; u < this._listeners.length; u++) r = this._listeners[u], (n == null || n == r.target) && (t == null || t == r.type) && (i == null || i == r.capture) && (r.target.removeEventListener(r.type, r.fn, r.capture), this._listeners.splice(u, 1), u--, f++);
                return f
            }, t.prototype.onGotFocus = function(n) {
                this.gotFocus.raise(this, n)
            }, t.prototype.onLostFocus = function(n) {
				console.log('onLostFocus begin')
                this.lostFocus.raise(this, n)
				console.log(this.lostFocus.hasHandlers)
				console.log('onLostFocus end')
            }, t.prototype._handleResize = function() {
                if (this._e.parentElement) {
                    var t = new n.Size(this._e.offsetWidth, this._e.offsetHeight);
                    t.equals(this._szCtl) || (this._szCtl = t, this.invalidate())
                }
            }, t.prototype._updateFocusState = function() {
                var i = this;
				console.log('_updateFocusState')
                setTimeout(function() {
                    for (var r, f, u = i._e; u; u = u.parentElement) r = t.getControl(u), r && (f = r.containsFocus(), f != r._focus && (r._focus = f, f ? r.onGotFocus() : r.onLostFocus(), n.toggleClass(u, 'wj-state-focused', f)))
                })
            }, t.prototype._handleTouchStart = function(n) {
                (n.pointerType == null || n.pointerType == 'touch') && (t._touching = !0)
            }, t.prototype._handleTouchEnd = function(n) {
                (n.pointerType == null || n.pointerType == 'touch') && setTimeout(function() {
                    t._touching = !1
                }, 400)
            }, t.prototype._handleDisabled = function(n) {
                this.disabled && (n.preventDefault(), n.stopPropagation(), n.stopImmediatePropagation())
            }, t.prototype._replaceWithDiv = function(n) {
                var f = n.parentElement,
                    i = document.createElement('div'),
                    r, t, u;
                for (f.replaceChild(i, n), i.innerHTML = n.innerHTML, r = n.attributes, t = 0; t < r.length; t++) u = r[t].name, (u == 'id' || u == 'style') && i.setAttribute(u, r[t].value);
                return i
            }, t.prototype._copyOriginalAttributes = function(n) {
                var r = this._orgAtts,
                    t, i;
                if (r)
                    for (t = 0; t < r.length; t++) i = r[t].name.toLowerCase(), i != 'id' && i != 'style' && i != 'type' && n.setAttribute(i, r[t].value)
            }, t._DATA_KEY = 'wj-Control', t._REFRESH_INTERVAL = 10, t
        }();
        n.Control = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';

        function i(i, r, u) {
            for (var e, o, v = 0, f = 0, l = 0, s = 0, h = null, c = null, y = u ? new n.Binding(u) : null, a = 0; a < r.length; a++) e = r[a], y && (e = y.getValue(e)), e != null && (v++, (h == null || e < h) && (h = e), (c == null || e > c) && (c = e), n.isNumber(e) && !isNaN(e) ? (f++, l += e, s += e * e) : n.isBoolean(e) && (f++, e == !0 && (l++, s++)));
            o = f == 0 ? 0 : l / f;
            switch (i) {
                case t.Avg:
                    return o;
                case t.Cnt:
                    return v;
                case t.Max:
                    return c;
                case t.Min:
                    return h;
                case t.Rng:
                    return c - h;
                case t.Sum:
                    return l;
                case t.VarPop:
                    return f <= 1 ? 0 : s / f - o * o;
                case t.StdPop:
                    return f <= 1 ? 0 : Math.sqrt(s / f - o * o);
                case t.Var:
                    return f <= 1 ? 0 : (s / f - o * o) * f / (f - 1);
                case t.Std:
                    return f <= 1 ? 0 : Math.sqrt((s / f - o * o) * f / (f - 1))
            }
            throw 'Invalid aggregate type.';
        }(function(n) {
            n[n.None = 0] = "None";
            n[n.Sum = 1] = "Sum";
            n[n.Cnt = 2] = "Cnt";
            n[n.Avg = 3] = "Avg";
            n[n.Max = 4] = "Max";
            n[n.Min = 5] = "Min";
            n[n.Rng = 6] = "Rng";
            n[n.Std = 7] = "Std";
            n[n.Var = 8] = "Var";
            n[n.StdPop = 9] = "StdPop";
            n[n.VarPop = 10] = "VarPop"
        })(n.Aggregate || (n.Aggregate = {}));
        var t = n.Aggregate;
        n.getAggregate = i
    }(wijmo || (wijmo = {}));
__extends = this && this.__extends || function(n, t) {
        function r() {
            this.constructor = n
        }
        for (var i in t) t.hasOwnProperty(i) && (n[i] = t[i]);
        n.prototype = t === null ? Object.create(t) : (r.prototype = t.prototype, new r)
    },
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i, u, f, e, r, o;
            (function(n) {
                n[n.Add = 0] = "Add";
                n[n.Remove = 1] = "Remove";
                n[n.Change = 2] = "Change";
                n[n.Reset = 3] = "Reset"
            })(t.NotifyCollectionChangedAction || (t.NotifyCollectionChangedAction = {}));
            i = t.NotifyCollectionChangedAction;
            u = function(n) {
                function t(t, r, u) {
                    t === void 0 && (t = i.Reset);
                    r === void 0 && (r = null);
                    u === void 0 && (u = -1);
                    n.call(this);
                    this.action = t;
                    this.item = r;
                    this.index = u
                }
                return __extends(t, n), t.reset = new t(i.Reset), t
            }(n.EventArgs);
            t.NotifyCollectionChangedEventArgs = u;
            f = function() {
                function t(t, i) {
                    this._bnd = new n.Binding(t);
                    this._asc = i
                }
                return Object.defineProperty(t.prototype, "property", {
                    get: function() {
                        return this._bnd.path
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "ascending", {
                    get: function() {
                        return this._asc
                    },
                    enumerable: !0,
                    configurable: !0
                }), t
            }();
            t.SortDescription = f;
            e = function(n) {
                function t(t) {
                    n.call(this);
                    this.newPageIndex = t
                }
                return __extends(t, n), t
            }(n.CancelEventArgs);
            t.PageChangingEventArgs = e;
            r = function() {
                function n() {}
                return n.prototype.groupNameFromItem = function() {
                    return ''
                }, n.prototype.namesMatch = function(n, t) {
                    return n === t
                }, n
            }();
            t.GroupDescription = r;
            o = function(t) {
                function i(i, r) {
                    t.call(this);
                    this._bnd = new n.Binding(i);
                    this._converter = r
                }
                return __extends(i, t), Object.defineProperty(i.prototype, "propertyName", {
                    get: function() {
                        return this._bnd.path
                    },
                    enumerable: !0,
                    configurable: !0
                }), i.prototype.groupNameFromItem = function(n) {
                    return this._converter ? this._converter(n, this.propertyName) : this._bnd.getValue(n)
                }, i.prototype.namesMatch = function(n, t) {
                    return n === t
                }, i
            }(r);
            t.PropertyGroupDescription = o
        })(t = n.collections || (n.collections = {}))
    }(wijmo || (wijmo = {}));
__extends = this && this.__extends || function(n, t) {
        function r() {
            this.constructor = n
        }
        for (var i in t) t.hasOwnProperty(i) && (n[i] = t[i]);
        n.prototype = t === null ? Object.create(t) : (r.prototype = t.prototype, new r)
    },
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                    function n() {
                        this.length = 0;
                        Array.apply(this, arguments)
                    }
                    return n.prototype.pop = function() {
                        return null
                    }, n.prototype.push = function() {
                        for (var t = [], n = 0; n < arguments.length; n++) t[+n] = arguments[n];
                        return 0
                    }, n.prototype.splice = function() {
                        return null
                    }, n.prototype.slice = function() {
                        return null
                    }, n.prototype.indexOf = function() {
                        return -1
                    }, n.prototype.sort = function() {
                        return null
                    }, n
                }(),
                r;
            t.ArrayBase = i;
            i.prototype = Array.prototype;
            r = function(i) {
                function r(t) {
                    if (i.call(this), this._updating = 0, this.collectionChanged = new n.Event, t) {
                        t = n.asArray(t);
                        this._updating++;
                        for (var r = 0; r < t.length; r++) this.push(t[r]);
                        this._updating--
                    }
                }
                return __extends(r, i), r.prototype.push = function() {
                    for (var f, r, n = [], u = 0; u < arguments.length; u++) n[+u] = arguments[u];
                    for (f = this.length, r = 0; n && r < n.length; r++) f = i.prototype.push.call(this, n[r]), this._updating || this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Add, n[r], f - 1);
                    return f
                }, r.prototype.pop = function() {
                    var n = i.prototype.pop.call(this);
                    return this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Remove, n, this.length), n
                }, r.prototype.splice = function(n, r, u) {
                    var f;
                    return r && u ? (f = i.prototype.splice.call(this, n, r, u), r == 1 ? this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Change, u, n) : this._raiseCollectionChanged(), f) : u ? (f = i.prototype.splice.call(this, n, 0, u), this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Add, u, n), f) : (f = i.prototype.splice.call(this, n, r), r == 1 ? this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Remove, f[0], n) : this._raiseCollectionChanged(), f)
                }, r.prototype.slice = function(n, t) {
                    return i.prototype.slice.call(this, n, t)
                }, r.prototype.indexOf = function(n, t) {
                    return i.prototype.indexOf.call(this, n, t)
                }, r.prototype.sort = function(n) {
                    var t = i.prototype.sort.call(this, n);
                    return this._raiseCollectionChanged(), t
                }, r.prototype.insert = function(n, t) {
                    this.splice(n, 0, t)
                }, r.prototype.remove = function(n) {
                    var t = this.indexOf(n);
                    return t > -1 ? (this.removeAt(t), !0) : !1
                }, r.prototype.removeAt = function(n) {
                    this.splice(n, 1)
                }, r.prototype.setAt = function(n, t) {
                    n > this.length && (this.length = n);
                    this.splice(n, 1, t)
                }, r.prototype.clear = function() {
                    this.length !== 0 && (this.splice(0, this.length), this._raiseCollectionChanged())
                }, r.prototype.beginUpdate = function() {
                    this._updating++
                }, r.prototype.endUpdate = function() {
                    this._updating > 0 && (this._updating--, this._updating == 0 && this._raiseCollectionChanged())
                }, Object.defineProperty(r.prototype, "isUpdating", {
                    get: function() {
                        return this._updating > 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), r.prototype.deferUpdate = function(n) {
                    try {
                        this.beginUpdate();
                        n()
                    } finally {
                        this.endUpdate()
                    }
                }, r.prototype.implementsInterface = function(n) {
                    return n == 'INotifyCollectionChanged'
                }, r.prototype.onCollectionChanged = function(n) {
                    n === void 0 && (n = t.NotifyCollectionChangedEventArgs.reset);
                    this.isUpdating || this.collectionChanged.raise(this, n)
                }, r.prototype._raiseCollectionChanged = function(n, i, r) {
                    if (!this.isUpdating) {
                        var u = new t.NotifyCollectionChangedEventArgs(n, i, r);
                        this.onCollectionChanged(u)
                    }
                }, r
            }(i);
            t.ObservableArray = r
        })(t = n.collections || (n.collections = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var r = function() {
                    function r(i) {
                        var r = this;
                        this._idx = -1;
                        this._srtDsc = new t.ObservableArray;
                        this._grpDesc = new t.ObservableArray;
                        this._newItem = null;
                        this._edtItem = null;
                        this._pgSz = 0;
                        this._pgIdx = 0;
                        this._updating = 0;
                        this._canFilter = !0;
                        this._canGroup = !0;
                        this._canSort = !0;
                        this._canAddNew = !0;
                        this._canCancelEdit = !0;
                        this._canRemove = !0;
                        this._canChangePage = !0;
                        this._trackChanges = !1;
                        this._chgAdded = new t.ObservableArray;
                        this._chgRemoved = new t.ObservableArray;
                        this._chgEdited = new t.ObservableArray;
                        this.collectionChanged = new n.Event;
                        this.currentChanged = new n.Event;
                        this.currentChanging = new n.Event;
                        this.pageChanged = new n.Event;
                        this.pageChanging = new n.Event;
                        this._srtDsc.collectionChanged.addHandler(function() {
                            for (var u = r._srtDsc, i = 0; i < u.length; i++) n.assert(u[i] instanceof t.SortDescription, 'sortDescriptions array must contain SortDescription objects.');
                            r.canSort && r.refresh()
                        });
                        this._grpDesc.collectionChanged.addHandler(function() {
                            for (var u = r._grpDesc, i = 0; i < u.length; i++) n.assert(u[i] instanceof t.GroupDescription, 'groupDescriptions array must contain GroupDescription objects.');
                            r.canGroup && r.refresh()
                        });
                        this.sourceCollection = i ? i : new t.ObservableArray
                    }
                    return Object.defineProperty(r.prototype, "newItemCreator", {
                        get: function() {
                            return this._itemCreator
                        },
                        set: function(t) {
                            this._itemCreator = n.asFunction(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "sortConverter", {
                        get: function() {
                            return this._srtCvt
                        },
                        set: function(t) {
                            t != this._srtCvt && (this._srtCvt = n.asFunction(t, !0))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "useStableSort", {
                        get: function() {
                            return this._stableSort
                        },
                        set: function(t) {
                            this._stableSort = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.implementsInterface = function(n) {
                        switch (n) {
                            case 'ICollectionView':
                            case 'IEditableCollectionView':
                            case 'IPagedCollectionView':
                            case 'INotifyCollectionChanged':
                                return !0
                        }
                        return !1
                    }, Object.defineProperty(r.prototype, "trackChanges", {
                        get: function() {
                            return this._trackChanges
                        },
                        set: function(t) {
                            this._trackChanges = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "itemsAdded", {
                        get: function() {
                            return this._chgAdded
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "itemsRemoved", {
                        get: function() {
                            return this._chgRemoved
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "itemsEdited", {
                        get: function() {
                            return this._chgEdited
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.clearChanges = function() {
                        this._chgAdded.clear();
                        this._chgRemoved.clear();
                        this._chgEdited.clear()
                    }, r.prototype.onCollectionChanged = function(n) {
                        n === void 0 && (n = t.NotifyCollectionChangedEventArgs.reset);
                        this.collectionChanged.raise(this, n)
                    }, r.prototype._raiseCollectionChanged = function(n, i, r) {
                        n === void 0 && (n = t.NotifyCollectionChangedAction.Reset);
                        var u = new t.NotifyCollectionChangedEventArgs(n, i, r);
                        this.onCollectionChanged(u)
                    }, Object.defineProperty(r.prototype, "canFilter", {
                        get: function() {
                            return this._canFilter
                        },
                        set: function(t) {
                            this._canFilter = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "canGroup", {
                        get: function() {
                            return this._canGroup
                        },
                        set: function(t) {
                            this._canGroup = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "canSort", {
                        get: function() {
                            return this._canSort
                        },
                        set: function(t) {
                            this._canSort = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "currentItem", {
                        get: function() {
                            return this._pgView && this._idx > -1 && this._idx < this._pgView.length ? this._pgView[this._idx] : null
                        },
                        set: function(n) {
                            this.moveCurrentTo(n)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "currentPosition", {
                        get: function() {
                            return this._idx
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "filter", {
                        get: function() {
                            return this._filter
                        },
                        set: function(t) {
                            this._filter != t && (this._filter = n.asFunction(t), this.canFilter && this.refresh())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "groupDescriptions", {
                        get: function() {
                            return this._grpDesc
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "groups", {
                        get: function() {
                            return this._groups
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "isEmpty", {
                        get: function() {
                            return this._pgView.length == 0
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "sortDescriptions", {
                        get: function() {
                            return this._srtDsc
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "sourceCollection", {
                        get: function() {
                            return this._src
                        },
                        set: function(t) {
                            if (t != this._src) {
                                var i = this.currentPosition;
                                this.commitEdit();
                                this.commitNew();
                                this._ncc != null && this._ncc.collectionChanged.removeHandler(this._sourceChanged);
                                this._src = n.asArray(t, !1);
                                this._ncc = n.tryCast(this._src, 'INotifyCollectionChanged');
                                this._ncc && this._ncc.collectionChanged.addHandler(this._sourceChanged, this);
                                this.clearChanges();
                                this.refresh();
                                this.moveCurrentToFirst();
                                this.currentPosition < 0 && i > -1 && this.onCurrentChanged()
                            }
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype._sourceChanged = function() {
                        this._updating <= 0 && this.refresh()
                    }, r.prototype.contains = function(n) {
                        return this._pgView.indexOf(n) > -1
                    }, r.prototype.moveCurrentTo = function(n) {
                        return this.moveCurrentToPosition(this._pgView.indexOf(n))
                    }, r.prototype.moveCurrentToFirst = function() {
                        return this.moveCurrentToPosition(0)
                    }, r.prototype.moveCurrentToLast = function() {
                        return this.moveCurrentToPosition(this._pgView.length - 1)
                    }, r.prototype.moveCurrentToNext = function() {
                        return this.moveCurrentToPosition(this._idx + 1)
                    }, r.prototype.moveCurrentToPosition = function(t) {
                        if (t >= -1 && t < this._pgView.length) {
                            var i = new n.CancelEventArgs;
                            this._idx != t && this.onCurrentChanging(i) && (this._edtItem && this._pgView[t] != this._edtItem && this.commitEdit(), this._newItem && this._pgView[t] != this._newItem && this.commitNew(), this._idx = t, this.onCurrentChanged())
                        }
                        return this._idx == t
                    }, r.prototype.moveCurrentToPrevious = function() {
                        return this.moveCurrentToPosition(this._idx - 1)
                    }, r.prototype.refresh = function() {
                        this._updating > 0 || this._newItem || this._edtItem || (this._performRefresh(), this.onCollectionChanged())
                    }, r.prototype._performRefresh = function() {
                        var i = this.currentItem,
                            t;
                        this._view = this._src ? this._filter && this.canFilter ? this._performFilter(this._src) : this._srtDsc.length > 0 && this.canSort ? this._src.slice(0) : this._src : [];
                        this._srtDsc.length > 0 && this.canSort && this._performSort(this._view);
                        this._groups = this.canGroup ? this._createGroups(this._view) : null;
                        this._fullGroups = this._groups;
                        this._groups && (this._view = this._mergeGroupItems(this._groups));
                        this._pgIdx = n.clamp(this._pgIdx, 0, this.pageCount - 1);
                        this._pgView = this._getPageView();
                        this._groups && this.pageCount > 1 && (this._groups = this._createGroups(this._pgView), this._mergeGroupItems(this._groups));
                        t = this._pgView.indexOf(i);
                        t < 0 && (t = Math.min(this._idx, this._pgView.length - 1));
                        this._idx = t;
                        this._digest = this._getGroupsDigest(this.groups);
                        this.currentItem !== i && this.onCurrentChanged()
                    }, r.prototype._performSort = function(n) {
                        var i, r, t;
                        if (this._stableSort)
                            for (i = n.map(function(n, t) {
                                    return {
                                        item: n,
                                        index: t
                                    }
                                }), r = this._compareItems(), i.sort(function(n, t) {
                                    var i = r(n.item, t.item);
                                    return i == 0 ? n.index - t.index : i
                                }), t = 0; t < n.length; t++) n[t] = i[t].item;
                        else n.sort(this._compareItems())
                    }, r.prototype._compareItems = function() {
                        var t = this._srtDsc,
                            n = this._srtCvt,
                            i = !0;
                        return function(r, u) {
                            for (var c, l, h, s = 0; s < t.length; s++) {
                                var o = t[s],
                                    f = o._bnd.getValue(r),
                                    e = o._bnd.getValue(u);
                                if (f !== f && (f = null), e !== e && (e = null), typeof f == 'string' && typeof e == 'string' && (c = f.toLowerCase(), l = e.toLowerCase(), c != l && (f = c, e = l)), n && (f = n(o, r, f, i), e = n(o, u, e, !1), i = !1), f != null && e == null) return -1;
                                if (f == null && e != null) return 1;
                                if (h = f < e ? -1 : f > e ? 1 : 0, h != 0) return o.ascending ? +h : -h
                            }
                            return 0
                        }
                    }, r.prototype._performFilter = function(n) {
                        return this.canFilter && this._filter ? n.filter(this._filter, this) : n
                    }, r.prototype.onCurrentChanged = function(t) {
                        t === void 0 && (t = n.EventArgs.empty);
                        this.currentChanged.raise(this, t)
                    }, r.prototype.onCurrentChanging = function(n) {
                        return this.currentChanging.raise(this, n), !n.cancel
                    }, Object.defineProperty(r.prototype, "items", {
                        get: function() {
                            return this._pgView
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.beginUpdate = function() {
                        this._updating++
                    }, r.prototype.endUpdate = function() {
                        this._updating--;
                        this._updating <= 0 && this.refresh()
                    }, Object.defineProperty(r.prototype, "isUpdating", {
                        get: function() {
                            return this._updating > 0
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.deferUpdate = function(n) {
                        try {
                            this.beginUpdate();
                            n()
                        } finally {
                            this.endUpdate()
                        }
                    }, Object.defineProperty(r.prototype, "canAddNew", {
                        get: function() {
                            return this._canAddNew
                        },
                        set: function(t) {
                            this._canAddNew = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "canCancelEdit", {
                        get: function() {
                            return this._canCancelEdit
                        },
                        set: function(t) {
                            this._canCancelEdit = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "canRemove", {
                        get: function() {
                            return this._canRemove
                        },
                        set: function(t) {
                            this._canRemove = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "currentAddItem", {
                        get: function() {
                            return this._newItem
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "currentEditItem", {
                        get: function() {
                            return this._edtItem
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "isAddingNew", {
                        get: function() {
                            return this._newItem != null
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "isEditingItem", {
                        get: function() {
                            return this._edtItem != null
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.addNew = function() {
                        var i, u, r;
                        if (arguments.length > 0 && n.assert(!1, 'addNew does not take any parameters, it creates the new items.'), this.commitEdit(), this.commitNew(), !this.canAddNew) return n.assert(!1, 'cannot add items (canAddNew == false).'), null;
                        if (i = null, u = this.sourceCollection, i = this.newItemCreator ? this.newItemCreator() : u && u.length ? new u[0].constructor : {}, i != null) {
                            if (this._newItem = i, this._updating++, this._src.push(i), this._updating--, this._pgView != this._src && this._pgView.push(i), this.groups && this.groups.length)
                                for (r = this.groups[this.groups.length - 1], r.items.push(i); r.groups && r.groups.length;) r = r.groups[r.groups.length - 1], r.items.push(i);
                            this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Add, i, this._pgView.length - 1);
                            this.moveCurrentTo(i)
                        }
                        return this._newItem
                    }, r.prototype.cancelEdit = function() {
                        var r = this._edtItem,
                            i;
                        if (r != null) {
                            if (this._edtItem = null, !this.canCancelEdit) {
                                n.assert(!1, 'cannot cancel edits (canCancelEdit == false).');
                                return
                            }
                            if (i = this._src.indexOf(r), i < 0 || !this._edtClone) return;
                            this._extend(this._src[i], this._edtClone);
                            this._edtClone = null;
                            this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Change, r, i)
                        }
                    }, r.prototype.cancelNew = function() {
                        var n = this._newItem;
                        n != null && this.remove(n)
                    }, r.prototype.commitEdit = function() {
                        var n = this._edtItem,
                            r, i, u;
                        n != null && (r = this._sameContent(n, this._edtClone), this._edtItem = null, this._edtClone = null, i = this._pgView.indexOf(n), u = this._digest, this._performRefresh(), this._trackChanges != !0 || r || this._trackItemChanged(n), this._pgView.indexOf(n) == i && u == this._digest ? this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Change, n, i) : this._raiseCollectionChanged())
                    }, r.prototype._trackItemChanged = function(n) {
                        var i, r;
                        if (this._trackChanges)
                            if (i = this._chgEdited.indexOf(n), i < 0 && this._chgAdded.indexOf(n) < 0) this._chgEdited.push(n);
                            else if (i > -1) {
                            r = new t.NotifyCollectionChangedEventArgs(t.NotifyCollectionChangedAction.Change, n, i);
                            this._chgEdited.onCollectionChanged(r)
                        } else if (i = this._chgAdded.indexOf(n), i > -1) {
                            r = new t.NotifyCollectionChangedEventArgs(t.NotifyCollectionChangedAction.Change, n, i);
                            this._chgAdded.onCollectionChanged(r)
                        }
                    }, r.prototype.commitNew = function() {
                        var n = this._newItem,
                            i, u, r;
                        n != null && (this._newItem = null, i = this._pgView.indexOf(n), u = this._digest, this._performRefresh(), this._trackChanges == !0 && (r = this._chgEdited.indexOf(n), r > -1 && this._chgEdited.removeAt(r), this._chgAdded.indexOf(n) < 0 && this._chgAdded.push(n)), this._pgView.indexOf(n) == i && u == this._digest ? this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Change, n, i) : this._raiseCollectionChanged())
                    }, r.prototype.editItem = function(n) {
                        n != this._edtItem && this.moveCurrentTo(n) && (this.commitEdit(), this._edtItem = n, this._edtClone = {}, this._extend(this._edtClone, this._edtItem))
                    }, r.prototype.remove = function(i) {
                        var e = i == this._newItem,
                            r, o, s, u, f, h, c, l;
                        if (e && (this._newItem = null), i == this._edtItem && this.cancelEdit(), !this.canRemove) {
                            n.assert(!1, 'cannot remove items (canRemove == false).');
                            return
                        }
                        r = this._src.indexOf(i);
                        r > -1 && (o = this.currentItem, this._updating++, this._src.splice(r, 1), this._updating--, s = this._digest, this._performRefresh(), this._trackChanges == !0 && (u = this._chgAdded.indexOf(i), u > -1 && this._chgAdded.removeAt(u), f = this._chgEdited.indexOf(i), f > -1 && this._chgEdited.removeAt(f), h = this._chgRemoved.indexOf(i), h < 0 && !e && u < 0 && this._chgRemoved.push(i)), c = this.sortDescriptions.length > 0, l = this.pageSize > 0 && this._pgIdx > -1, c || l || s != this._getGroupsDigest(this.groups) ? this._raiseCollectionChanged() : this._raiseCollectionChanged(t.NotifyCollectionChangedAction.Remove, i, r), this.currentItem !== o && this.onCurrentChanged())
                    }, r.prototype.removeAt = function(t) {
                        t = n.asInt(t);
                        this.remove(this._pgView[t])
                    }, r.prototype._extend = function(n, t) {
                        for (var i in t) n[i] = t[i]
                    }, r.prototype._sameContent = function(n, t) {
                        for (var i in t)
                            if (!this._sameValue(n[i], t[i])) return !1;
                        for (i in n)
                            if (!this._sameValue(n[i], t[i])) return !1;
                        return !0
                    }, r.prototype._sameValue = function(t, i) {
                        return t == i || n.DateTime.equals(t, i)
                    }, Object.defineProperty(r.prototype, "canChangePage", {
                        get: function() {
                            return this._canChangePage
                        },
                        set: function(t) {
                            this._canChangePage = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "isPageChanging", {
                        get: function() {
                            return !1
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "itemCount", {
                        get: function() {
                            return this._pgView.length
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "pageIndex", {
                        get: function() {
                            return this._pgIdx
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "pageSize", {
                        get: function() {
                            return this._pgSz
                        },
                        set: function(t) {
                            t != this._pgSz && (this._pgSz = n.asInt(t), this.refresh())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "totalItemCount", {
                        get: function() {
                            return this._view.length
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "pageCount", {
                        get: function() {
                            return this.pageSize ? Math.ceil(this.totalItemCount / this.pageSize) : 1
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.moveToFirstPage = function() {
                        return this.moveToPage(0)
                    }, r.prototype.moveToLastPage = function() {
                        return this.moveToPage(this.pageCount - 1)
                    }, r.prototype.moveToNextPage = function() {
                        return this.moveToPage(this.pageIndex + 1)
                    }, r.prototype.moveToPage = function(i) {
                        var r = n.clamp(i, 0, this.pageCount - 1),
                            u;
                        return r != this._pgIdx && (this.canChangePage || n.assert(!1, 'cannot change pages (canChangePage == false).'), u = new t.PageChangingEventArgs(r), this.onPageChanging(u) && (this._pgIdx = r, this._pgView = this._getPageView(), this._idx = 0, this.groupDescriptions && this.groupDescriptions.length != 0 ? this.refresh() : (this.onPageChanged(), this.onCollectionChanged()))), this._pgIdx == i
                    }, r.prototype.moveToPreviousPage = function() {
                        return this.moveToPage(this.pageIndex - 1)
                    }, r.prototype.onPageChanged = function(t) {
                        t === void 0 && (t = n.EventArgs.empty);
                        this.pageChanged.raise(this, t)
                    }, r.prototype.onPageChanging = function(n) {
                        return this.pageChanging.raise(this, n), !n.cancel
                    }, r.prototype._getFullGroup = function(n) {
                        var t = this._getGroupByPath(this._fullGroups, n.level, n._path);
                        return t != null && (n = t), n
                    }, r.prototype._getGroupByPath = function(n, t, i) {
                        for (var r, u = 0; u < n.length; u++)
                            if ((r = n[u], r.level == t && r._path == i) || r.level < t && i.indexOf(r._path) == 0 && (r = this._getGroupByPath(r.groups, t, i), r != null)) return r;
                        return null
                    }, r.prototype._getPageView = function() {
                        if (this.pageSize <= 0 || this._pgIdx < 0) return this._view;
                        var n = this._pgSz * this._pgIdx,
                            t = Math.min(n + this._pgSz, this._view.length);
                        return this._view.slice(n, t)
                    }, r.prototype._createGroups = function(t) {
                        var u, i, e;
                        if (!this._grpDesc || !this._grpDesc.length) return null;
                        var s = [],
                            h = {},
                            r = null;
                        for (u = 0; u < t.length; u++) {
                            var c = t[u],
                                l = s,
                                a = this._grpDesc.length,
                                f = '';
                            for (i = 0; i < a; i++) {
                                var v = this._grpDesc[i],
                                    o = v.groupNameFromItem(c, i),
                                    y = i == a - 1;
                                r = h[f];
                                !r && n.isPrimitive(o) && (r = {}, h[f] = r);
                                e = this._getGroup(v, l, r, o, i, y);
                                f += '/' + o;
                                e._path = f;
                                y && e.items.push(c);
                                l = e.groups
                            }
                        }
                        return s
                    }, r.prototype._getGroupsDigest = function(n) {
                        for (var t, i = '', r = 0; n != null && r < n.length; r++) t = n[r], i += '{' + t.name + ':' + (t.items ? t.items.length : '*'), t.groups.length > 0 && (i += ',', i += this._getGroupsDigest(t.groups)), i += '}';
                        return i
                    }, r.prototype._mergeGroupItems = function(n) {
                        for (var t, u, i = [], r = 0; r < n.length; r++) t = n[r], t._isBottomLevel || (u = this._mergeGroupItems(t.groups), t._items.push.apply(t._items, u)), i.push.apply(i, t._items);
                        return i
                    }, r.prototype._getGroup = function(t, r, u, f, e, o) {
                        var c, s, h;
                        if (u && n.isPrimitive(f)) {
                            if (c = u[f], c) return c
                        } else
                            for (s = 0; s < r.length; s++)
                                if (t.namesMatch(r[s].name, f)) return r[s]; return h = new i(t, f, e, o), r.push(h), u && (u[f] = h), h
                    }, r
                }(),
                i;
            t.CollectionView = r;
            i = function() {
                function t(n, t, i, r) {
                    this._gd = n;
                    this._name = t;
                    this._level = i;
                    this._isBottomLevel = r;
                    this._groups = [];
                    this._items = []
                }
                return Object.defineProperty(t.prototype, "name", {
                    get: function() {
                        return this._name
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "level", {
                    get: function() {
                        return this._level
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "isBottomLevel", {
                    get: function() {
                        return this._isBottomLevel
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "items", {
                    get: function() {
                        return this._items
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "groups", {
                    get: function() {
                        return this._groups
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "groupDescription", {
                    get: function() {
                        return this._gd
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype.getAggregate = function(t, i, u) {
                    var f = n.tryCast(u, r),
                        e = f ? f._getFullGroup(this) : this;
                    return n.getAggregate(t, e.items, i)
                }, t
            }();
            t.CollectionViewGroup = i
        })(t = n.collections || (n.collections = {}))
    }(wijmo || (wijmo = {}));
__extends = this && this.__extends || function(n, t) {
        function r() {
            this.constructor = n
        }
        for (var i in t) t.hasOwnProperty(i) && (n[i] = t[i]);
        n.prototype = t === null ? Object.create(t) : (r.prototype = t.prototype, new r)
    },
    function(n) {
        'use strict';
        var i = function() {
                function i(t) {
                    this._showAutoTipBnd = this._showAutoTip.bind(this);
                    this._hideAutoTipBnd = this._hideAutoTip.bind(this);
                    this._html = !0;
                    this._gap = 6;
                    this._showAtMouse = !1;
                    this._showDelay = 500;
                    this._hideDelay = 0;
                    this._tips = [];
                    this.popup = new n.Event;
                    n.copy(this, t)
                }
                return i.prototype.setTooltip = function(t, i) {
                    t = n.getElement(t);
                    i = this._getContent(i);
                    var r = this._indexOf(t);
                    r > -1 && (this._detach(t), this._tips.splice(r, 1));
                    i && (this._attach(t), this._tips.push({
                        element: t,
                        content: i
                    }))
                }, i.prototype.show = function(r, u, f) {
                    var e, o;
                    r = n.getElement(r);
                    u = this._getContent(u);
                    f || (f = n.Rect.fromBoundingRect(r.getBoundingClientRect()));
                    e = i._eTip;
                    e || (e = i._eTip = document.createElement('div'), n.addClass(e, 'wj-tooltip'), e.style.visibility = 'none', document.body.appendChild(e));
                    this._setContent(u);
                    o = new t(u);
                    this.onPopup(o);
                    o.content && !o.cancel && (this._setContent(o.content), e.style.minWidth = '', f = new n.Rect(f.left - (e.offsetWidth - f.width) / 2, f.top - this.gap, e.offsetWidth, f.height + 2 * this.gap), n.showPopup(e, f, !0), document.addEventListener('mousedown', this._hideAutoTipBnd))
                }, i.prototype.hide = function() {
                    i._eTip && (i._eTip.style.visibility = 'hidden', i._eTip.innerHTML = '');
                    document.removeEventListener('mousedown', this._hideAutoTipBnd)
                }, Object.defineProperty(i.prototype, "isVisible", {
                    get: function() {
                        return i._eTip && i._eTip.style.visibility != 'hidden'
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "isContentHtml", {
                    get: function() {
                        return this._html
                    },
                    set: function(t) {
                        this._html = n.asBoolean(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "gap", {
                    get: function() {
                        return this._gap
                    },
                    set: function(t) {
                        this._gap = n.asNumber(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "showAtMouse", {
                    get: function() {
                        return this._showAtMouse
                    },
                    set: function(t) {
                        this._showAtMouse = n.asBoolean(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "showDelay", {
                    get: function() {
                        return this._showDelay
                    },
                    set: function(t) {
                        this._showDelay = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "hideDelay", {
                    get: function() {
                        return this._hideDelay
                    },
                    set: function(t) {
                        this._hideDelay = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), i.prototype.onPopup = function(n) {
                    this.popup && this.popup.raise(this, n)
                }, i.prototype._indexOf = function(n) {
                    for (var t = 0; t < this._tips.length; t++)
                        if (this._tips[t].element == n) return t;
                    return -1
                }, i.prototype._attach = function(n) {
                    n.addEventListener('mouseenter', this._showAutoTipBnd);
                    n.addEventListener('mouseleave', this._hideAutoTipBnd);
                    n.addEventListener('click', this._showAutoTipBnd)
                }, i.prototype._detach = function(n) {
                    n.removeEventListener('mouseenter', this._showAutoTipBnd);
                    n.removeEventListener('mouseleave', this._hideAutoTipBnd);
                    n.removeEventListener('click', this._showAutoTipBnd)
                }, i.prototype._showAutoTip = function(t) {
                    var i = this,
                        r = t.type == 'mouseenter' ? this._showDelay : 0;
                    this._clearTimeouts();
                    this._toShow = setTimeout(function() {
                        var u = i._indexOf(t.target),
                            r, f;
                        u > -1 && (r = i._tips[u], f = i._showAtMouse ? new n.Rect(t.clientX, t.clientY, 0, 0) : null, i.show(r.element, r.content, f), i._hideDelay > 0 && (i._toHide = setTimeout(function() {
                            i.hide()
                        }, i._hideDelay)))
                    }, r)
                }, i.prototype._hideAutoTip = function() {
                    this._clearTimeouts();
                    this.hide()
                }, i.prototype._clearTimeouts = function() {
                    this._toShow && (clearTimeout(this._toShow), this._toShow = null);
                    this._toHide && (clearTimeout(this._toHide), this._toHide = null)
                }, i.prototype._getContent = function(t) {
                    if (t = n.asString(t), t && t[0] == '#') {
                        var i = n.getElement(t);
                        i && (t = i.innerHTML)
                    }
                    return t
                }, i.prototype._setContent = function(n) {
                    var t = i._eTip;
                    t && (this.isContentHtml ? t.innerHTML = n : t.textContent = n)
                }, i
            }(),
            r, t;
        n.Tooltip = i;
        r = function() {
            function n() {}
            return n
        }();
        t = function(t) {
            function i(i) {
                t.call(this);
                this._content = n.asString(i)
            }
            return __extends(i, t), Object.defineProperty(i.prototype, "content", {
                get: function() {
                    return this._content
                },
                set: function(t) {
                    this._content = n.asString(t)
                },
                enumerable: !0,
                configurable: !0
            }), i
        }(n.CancelEventArgs);
        n.TooltipEventArgs = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';
        var t = function() {
            function t(n) {
                this._r = 0;
                this._g = 0;
                this._b = 0;
                this._a = 1;
                n && this._parse(n)
            }
            return Object.defineProperty(t.prototype, "r", {
                get: function() {
                    return this._r
                },
                set: function(t) {
                    this._r = n.clamp(n.asNumber(t), 0, 255)
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "g", {
                get: function() {
                    return this._g
                },
                set: function(t) {
                    this._g = n.clamp(n.asNumber(t), 0, 255)
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "b", {
                get: function() {
                    return this._b
                },
                set: function(t) {
                    this._b = n.clamp(n.asNumber(t), 0, 255)
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "a", {
                get: function() {
                    return this._a
                },
                set: function(t) {
                    this._a = n.clamp(n.asNumber(t), 0, 1)
                },
                enumerable: !0,
                configurable: !0
            }), t.prototype.equals = function(n) {
                return n instanceof t && this.r == n.r && this.g == n.g && this.b == n.b && this.a == n.a
            }, t.prototype.toString = function() {
                var n = Math.round(this.a * 100);
                return n > 99 ? '#' + (16777216 + (this.r << 16) + (this.g << 8) + this.b).toString(16).slice(1) : 'rgba(' + this.r + ',' + this.g + ',' + this.b + ',' + n / 100 + ')'
            }, t.fromRgba = function(i, r, u, f) {
                f === void 0 && (f = 1);
                var e = new t(null);
                return e.r = Math.round(n.clamp(n.asNumber(i), 0, 255)), e.g = Math.round(n.clamp(n.asNumber(r), 0, 255)), e.b = Math.round(n.clamp(n.asNumber(u), 0, 255)), e.a = n.clamp(n.asNumber(f), 0, 1), e
            }, t.fromHsb = function(i, r, u, f) {
                f === void 0 && (f = 1);
                var e = t._hsbToRgb(n.clamp(n.asNumber(i), 0, 1), n.clamp(n.asNumber(r), 0, 1), n.clamp(n.asNumber(u), 0, 1));
                return t.fromRgba(e[0], e[1], e[2], f)
            }, t.fromHsl = function(i, r, u, f) {
                f === void 0 && (f = 1);
                var e = t._hslToRgb(n.clamp(n.asNumber(i), 0, 1), n.clamp(n.asNumber(r), 0, 1), n.clamp(n.asNumber(u), 0, 1));
                return t.fromRgba(e[0], e[1], e[2], f)
            }, t.fromString = function(i) {
                var r = new t(null);
                return r._parse(n.asString(i)) ? r : null
            }, t.prototype.getHsb = function() {
                return t._rgbToHsb(this.r, this.g, this.b)
            }, t.prototype.getHsl = function() {
                return t._rgbToHsl(this.r, this.g, this.b)
            }, t.interpolate = function(i, r, u) {
                u = n.clamp(n.asNumber(u), 0, 1);
                var e = t._rgbToHsl(i.r, i.g, i.b),
                    o = t._rgbToHsl(r.r, r.g, r.b),
                    f = 1 - u,
                    c = i.a * f + r.a * u,
                    s = [e[0] * f + o[0] * u, e[1] * f + o[1] * u, e[2] * f + o[2] * u],
                    h = t._hslToRgb(s[0], s[1], s[2]);
                return t.fromRgba(h[0], h[1], h[2], c)
            }, t.toOpaque = function(i, r) {
                if (i = n.isString(i) ? t.fromString(i) : n.asType(i, t), i.a == 1) return i;
                r = r == null ? t.fromRgba(255, 255, 255, 1) : n.isString(r) ? t.fromString(r) : n.asType(r, t);
                var u = i.a,
                    f = 1 - u;
                return t.fromRgba(i.r * u + r.r * f, i.g * u + r.g * f, i.b * u + r.b * f)
            }, t.prototype._parse = function(n) {
                var u, f, r, e, i, o;
                if (n = n.toLowerCase(), n == 'transparent') return this._r = this._g = this._b = this._a = 0, !0;
                if (n && n.indexOf('#') != 0 && n.indexOf('rgb') != 0 && n.indexOf('hsl') != 0 && (u = document.createElement('div'), u.style.color = n, f = u.style.color, f == n && (f = window.getComputedStyle(u).color, f || (document.body.appendChild(u), f = window.getComputedStyle(u).color, document.body.removeChild(u))), n = f.toLowerCase()), n.indexOf('#') == 0) return n.length == 4 ? (this.r = parseInt(n[1] + n[1], 16), this.g = parseInt(n[2] + n[2], 16), this.b = parseInt(n[3] + n[3], 16), this.a = 1, !0) : n.length == 7 ? (this.r = parseInt(n.substr(1, 2), 16), this.g = parseInt(n.substr(3, 2), 16), this.b = parseInt(n.substr(5, 2), 16), this.a = 1, !0) : !1;
                if (n.indexOf('rgb') == 0 && (r = n.indexOf('('), e = n.indexOf(')'), r > -1 && e > -1 && (i = n.substr(r + 1, e - (r + 1)).split(','), i.length > 2))) return this.r = parseInt(i[0]), this.g = parseInt(i[1]), this.b = parseInt(i[2]), this.a = i.length > 3 ? parseFloat(i[3]) : 1, !0;
                if (n.indexOf('hsl') == 0 && (r = n.indexOf('('), e = n.indexOf(')'), r > -1 && e > -1 && (i = n.substr(r + 1, e - (r + 1)).split(','), i.length > 2))) {
                    var c = parseInt(i[0]) / 360,
                        s = parseInt(i[1]),
                        h = parseInt(i[2]);
                    return i[1].indexOf('%') > -1 && (s /= 100), i[2].indexOf('%') > -1 && (h /= 100), o = t._hslToRgb(c, s, h), this.r = o[0], this.g = o[1], this.b = o[2], this.a = i.length > 3 ? parseFloat(i[3]) : 1, !0
                }
                return !1
            }, t._hslToRgb = function(i, r, u) {
                var o, s, h, f, e;
                return n.assert(i >= 0 && i <= 1 && r >= 0 && r <= 1 && u >= 0 && u <= 1, 'bad HSL values'), r == 0 ? o = s = h = u : (f = u < .5 ? u * (1 + r) : u + r - u * r, e = 2 * u - f, o = t._hue2rgb(e, f, i + 1 / 3), s = t._hue2rgb(e, f, i), h = t._hue2rgb(e, f, i - 1 / 3)), [Math.round(o * 255), Math.round(s * 255), Math.round(h * 255)]
            }, t._hue2rgb = function(n, t, i) {
                return (i < 0 && (i += 1), i > 1 && (i -= 1), i < 1 / 6) ? n + (t - n) * 6 * i : i < 1 / 2 ? t : i < 2 / 3 ? n + (t - n) * (2 / 3 - i) * 6 : n
            }, t._rgbToHsl = function(t, i, r) {
                var e;
                n.assert(t >= 0 && t <= 255 && i >= 0 && i <= 255 && r >= 0 && r <= 255, 'bad RGB values');
                t /= 255;
                i /= 255;
                r /= 255;
                var u = Math.max(t, i, r),
                    o = Math.min(t, i, r),
                    f, s, h = (u + o) / 2;
                if (u == o) f = s = 0;
                else {
                    e = u - o;
                    s = h > .5 ? e / (2 - u - o) : e / (u + o);
                    switch (u) {
                        case t:
                            f = (i - r) / e + (i < r ? 6 : 0);
                            break;
                        case i:
                            f = (r - t) / e + 2;
                            break;
                        case r:
                            f = (t - i) / e + 4
                    }
                    f /= 6
                }
                return [f, s, h]
            }, t._rgbToHsb = function(i, r, u) {
                n.assert(i >= 0 && i <= 255 && r >= 0 && r <= 255 && u >= 0 && u <= 255, 'bad RGB values');
                var f = t._rgbToHsl(i, r, u);
                return t._hslToHsb(f[0], f[1], f[2])
            }, t._hsbToRgb = function(n, i, r) {
                var u = t._hsbToHsl(n, i, r);
                return t._hslToRgb(u[0], u[1], u[2])
            }, t._hsbToHsl = function(t, i, r) {
                n.assert(t >= 0 && t <= 1 && i >= 0 && i <= 1 && r >= 0 && r <= 1, 'bad HSB values');
                var u = n.clamp(r * (2 - i) / 2, 0, 1),
                    f = 1 - Math.abs(2 * u - 1),
                    e = n.clamp(f > 0 ? r * i / f : i, 0, 1);
                return n.assert(!isNaN(u) && !isNaN(e), 'bad conversion to HSL'), [t, e, u]
            }, t._hslToHsb = function(t, i, r) {
                n.assert(t >= 0 && t <= 1 && i >= 0 && i <= 1 && r >= 0 && r <= 1, 'bad HSL values');
                var u = n.clamp(r == 1 ? 1 : (2 * r + i * (1 - Math.abs(2 * r - 1))) / 2, 0, 1),
                    f = n.clamp(u > 0 ? 2 * (u - r) / u : i, 0, 1);
                return n.assert(!isNaN(u) && !isNaN(f), 'bad conversion to HSB'), [t, f, u]
            }, t
        }();
        n.Color = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';
        var t = function() {
            function n() {}
            return n.copy = function(t) {
                n._copyPasteInternal(t)
            }, n.paste = function(t) {
                n._copyPasteInternal(t)
            }, n._copyPasteInternal = function(n) {
                for (var r = document.activeElement, i = r, t; i; i = i.parentElement)
                    if (i == document.body || i.getAttribute('aria-describedby') == 'dialog') break;
                t = document.createElement('textarea');
                t.style.position = 'fixed';
                t.style.opacity = '0';
                i.appendChild(t);
                typeof n == 'string' && (t.value = n);
                t.select();
                setTimeout(function() {
                    var u = t.value;
                    i.removeChild(t);
                    r.focus();
                    typeof n == 'function' && n(u)
                }, 100)
            }, n
        }();
        n.Clipboard = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';

        function i(i, r, u, f, e) {
            var a, v, h, nt, p, d, tt, g, w, s;
            if (r === void 0 && (r = null), u === void 0 && (u = !1), f === void 0 && (f = !1), e === void 0 && (e = !0), a = document.body, r instanceof HTMLElement) {
                if (!n.contains(document.body, r)) return;
                for (v = r.parentElement; v; v = v.parentElement)
                    if (getComputedStyle(v).position == 'fixed') {
                        a = v;
                        break
                    }
            }
            a != i.parentElement && a.appendChild(i);
            r instanceof HTMLElement && e && (h = getComputedStyle(r), nt = new n.Color(h.backgroundColor), nt.a && n.setCss(i, {
                color: h.color,
                backgroundColor: h.backgroundColor,
                fontFamily: h.fontFamily,
                fontSize: h.fontSize,
                fontWeight: h.fontWeight,
                fontStyle: h.fontStyle
            }));
            n.setCss(i, {
                position: 'absolute',
                visibility: 'hidden',
                display: ''
            });
            i.firstElementChild && n.Control.refreshAll(i.firstElementChild);
            var y = getComputedStyle(i),
                it = parseFloat(y.marginTop) + parseFloat(y.marginBottom),
                rt = parseFloat(y.marginLeft) + parseFloat(y.marginRight),
                c = new n.Size(i.offsetWidth + rt, i.offsetHeight + it),
                l = new n.Point,
                o = null;
            if (r instanceof MouseEvent) r.clientX <= 0 && r.clientY <= 0 && r.target ? o = r.target.getBoundingClientRect() : (l.x = Math.max(0, r.pageX - pageXOffset), l.y = Math.max(0, r.pageY - pageYOffset));
            else if (r instanceof HTMLElement) o = r.getBoundingClientRect();
            else if (r && r.top != null && r.left != null) o = r;
            else if (r == null) l.x = Math.max(0, (innerWidth - c.width) / 2), l.y = Math.max(0, Math.round((innerHeight - c.height) / 2 * .7));
            else throw 'Invalid ref parameter.';
            if (p = parseFloat(y.minWidth), o) {
                var b = o.top,
                    k = innerHeight - o.bottom,
                    ut = getComputedStyle(i).direction == 'rtl';
                l.x = ut ? Math.max(0, o.right - c.width) : Math.max(0, Math.min(o.left, innerWidth - c.width));
                l.y = u ? b > c.height || b > k ? Math.max(0, o.top - c.height) : o.bottom : k > c.height || k > b ? o.bottom : Math.max(0, o.top - c.height);
                p = Math.max(p, o.width)
            }
            if (d = r == null ? new n.Rect(0, 0, 0, 0) : a == document.body ? new n.Rect(-pageXOffset, -pageYOffset, 0, 0) : a.getBoundingClientRect(), tt = {
                    position: r == null ? 'fixed' : 'absolute',
                    left: l.x - d.left,
                    top: l.y - d.top,
                    minWidth: p,
                    display: '',
                    visibility: 'visible',
                    zIndex: 1500
                }, f && (i.style.opacity = '0', n.animate(function(n) {
                    i.style.opacity = n == 1 ? '' : n.toString()
                })), n.setCss(i, tt), g = r instanceof MouseEvent ? r.target : r, g instanceof HTMLElement)
                for (s = g.parentElement; s && s != document.body; s = s.parentElement) getComputedStyle(s).overflowY == 'auto' && s.scrollHeight > s.offsetHeight && (w || (w = new n.Control(document.createElement('div'))), w.addEventListener(s, 'scroll', function() {
                    t(i, !0);
                    w.dispose()
                }))
        }

        function r(i, r, u) {
            r === void 0 && (r = !0);
            u === void 0 && (u = !1);
            u ? n.animate(function(n) {
                i.style.opacity = (1 - n).toString();
                n == 1 && (t(i, r), i.style.opacity = '')
            }) : t(i, r)
        }

        function t(n, t) {
            n.style.display = 'none';
            t && n.parentElement && n.parentElement.removeChild(n)
        }
        n.showPopup = i;
        n.hidePopup = r
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';
        var t = function() {
            function t(t) {
                this._copyCss = !0;
                t != null && n.copy(this, t)
            }
            return Object.defineProperty(t.prototype, "title", {
                get: function() {
                    return this._title
                },
                set: function(t) {
                    this._title = n.asString(t)
                },
                enumerable: !0,
                configurable: !0
            }), Object.defineProperty(t.prototype, "copyCss", {
                get: function() {
                    return this._copyCss
                },
                set: function(t) {
                    this._copyCss = n.asBoolean(t)
                },
                enumerable: !0,
                configurable: !0
            }), t.prototype.addCSS = function(n) {
                this._css || (this._css = []);
                this._css.push(n)
            }, t.prototype.append = function(t) {
                var i = this._getDocument();
                n.isString(t) ? i.write(t) : t instanceof HTMLElement ? i.write(t.outerHTML) : n.assert(!1, 'child should be an HTML element or a string.')
            }, t.prototype.print = function() {
                var n = this;
                this._iframe && (this._close(), setTimeout(function() {
                    var t = n._iframe.contentWindow;
                    t.focus();
                    t.print();
                    document.body.removeChild(n._iframe);
                    n._iframe = null
                }, 100))
            }, t.prototype._getDocument = function() {
                if (!this._iframe) {
                    this._iframe = document.createElement('iframe');
                    var n = this._iframe.style;
                    n.position = 'fixed';
                    n.left = '10000px';
                    n.top = '10000px';
                    document.body.appendChild(this._iframe)
                }
                return this._iframe.contentDocument
            }, t.prototype._close = function() {
                var i = this._getDocument(),
                    r, e, u, o, t, s, f;
                if (i.close(), this.title && (r = i.querySelector('title'), r || (r = i.createElement('title'), i.head.appendChild(r)), r.textContent = this.title), this._copyCss) {
                    for (e = document.head.querySelectorAll('LINK'), t = 0; t < e.length; t++) u = e[t], u.href.match(/\.css$/i) && u.rel.match(/stylesheet/i) && (f = n.httpRequest(u.href, {
                        async: !1
                    }), this._addStyle(f.responseText));
                    for (o = document.head.querySelectorAll('STYLE'), t = 0; t < o.length; t++) this._addStyle(o[t].textContent)
                }
                if (this._css)
                    for (t = 0; t < this._css.length; t++) s = i.createElement('style'), f = n.httpRequest(this._css[t], {
                        async: !1
                    }), s.textContent = f.responseText, i.head.appendChild(s)
            }, t.prototype._addStyle = function(n) {
                var t = this._getDocument(),
                    i = t.createElement('style');
                i.textContent = n;
                t.head.appendChild(i)
            }, t
        }();
        n.PrintDocument = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';
        var i = function() {
                function i(n, t, i) {
                    t === void 0 && (t = null);
                    i === void 0 && (i = '_');
                    this._promptChar = '_';
                    this._mskArr = [];
                    this._full = !0;
                    this._hbInput = this._input.bind(this);
                    this._hbKeyDown = this._keydown.bind(this);
                    this._hbKeyPress = this._keypress.bind(this);
                    this._hbCompositionStart = this._compositionstart.bind(this);
                    this._hbCompositionEnd = this._compositionend.bind(this);
                    this.mask = t;
                    this.input = n;
                    this.promptChar = i;
                    this._connect(!0)
                }
                return Object.defineProperty(i.prototype, "input", {
                    get: function() {
                        return this._tbx
                    },
                    set: function(n) {
                        this._connect(!1);
                        this._tbx = n;
                        this._connect(!0)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "mask", {
                    get: function() {
                        return this._msk
                    },
                    set: function(t) {
                        t != this._msk && (this._msk = n.asString(t, !0), this._parseMask(), this._valueChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "promptChar", {
                    get: function() {
                        return this._promptChar
                    },
                    set: function(t) {
                        t != this._promptChar && (this._promptChar = n.asString(t, !1), n.assert(this._promptChar.length == 1, 'promptChar must be a string with length 1.'), this._valueChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "maskFull", {
                    get: function() {
                        return this._full
                    },
                    enumerable: !0,
                    configurable: !0
                }), i.prototype.getMaskRange = function() {
                    return this._mskArr.length ? [this._firstPos, this._lastPos] : [0, this._tbx.value.length - 1]
                }, i.prototype.getRawValue = function() {
                    var t = this._tbx.value,
                        i = '',
                        n;
                    if (!this.mask) return t;
                    for (n = 0; n < this._mskArr.length && n < t.length; n++) this._mskArr[n].literal || t[n] == this._promptChar || (i += t[n]);
                    return i
                }, i.prototype.refresh = function() {
                    this._parseMask();
                    this._valueChanged()
                }, i.prototype._input = function() {
                    var n = this;
                    this._composing || setTimeout(function() {
                        n._valueChanged()
                    })
                }, i.prototype._keydown = function(t) {
                    this._backSpace = t.keyCode == n.Key.Back
                }, i.prototype._keypress = function(n) {
                    n.ctrlKey || n.metaKey || n.altKey || this._composing || !this._preventKey(n.charCode) || n.preventDefault()
                }, i.prototype._compositionstart = function() {
                    this._composing = !0
                }, i.prototype._compositionend = function() {
                    var n = this;
                    this._composing = !1;
                    setTimeout(function() {
                        n._valueChanged()
                    })
                }, i.prototype._preventKey = function(t) {
                    var r;
                    if (t && this._mskArr.length) {
                        var u = this._tbx,
                            i = u.selectionStart,
                            f = String.fromCharCode(t);
                        if (i < this._firstPos && (i = this._firstPos, n.setSelectionRange(u, i)), i >= this._mskArr.length) return !0;
                        if (r = this._mskArr[i], r.literal) this._validatePosition(i);
                        else if (r.wildCard != f && !this._isCharValid(r.wildCard, f)) return !0
                    }
                    return !1
                }, i.prototype._connect = function(n) {
                    var t = this._tbx;
                    t && (n ? (this._autoComplete = t.autocomplete, this._spellCheck = t.spellcheck, t.autocomplete = 'off', t.spellcheck = !1, t.addEventListener('input', this._hbInput), t.addEventListener('keydown', this._hbKeyDown, !0), t.addEventListener('keypress', this._hbKeyPress, !0), t.addEventListener('compositionstart', this._hbCompositionStart, !0), t.addEventListener('compositionend', this._hbCompositionEnd, !0), this._valueChanged()) : (t.autocomplete = this._autoComplete, t.spellcheck = this._spellCheck, t.removeEventListener('input', this._hbInput), t.removeEventListener('keydown', this._hbKeyDown, !0), t.removeEventListener('keypress', this._hbKeyPress, !0), t.removeEventListener('compositionstart', this._hbCompositionStart, !0), t.removeEventListener('compositionend', this._hbCompositionEnd, !0)))
                }, i.prototype._valueChanged = function() {
                    var t = this._tbx,
                        n, i, r;
                    t && (n = t.selectionStart, i = n > 0 ? t.value[n - 1] : '', t.value = this._applyMask(), r = n > 0 ? t.value[n - 1] : '', n > 0 && r == this._promptChar && i != this.promptChar && n--, this._validatePosition(n))
                }, i.prototype._applyMask = function() {
                    var t = this._tbx.value,
                        e = '',
                        u = 0,
                        f, r, n, i;
                    if (this._full = !0, !this.mask) return t;
                    for (t = this._handleVagueLiterals(t), f = 0; f < this._mskArr.length; f++) {
                        if (r = this._mskArr[f], n = r.literal, n && n == t[u] && u++, r.wildCard) {
                            if (n = this._promptChar, t) {
                                for (i = u; i < t.length; i++)
                                    if (this._isCharValid(r.wildCard, t[i])) {
                                        n = t[i];
                                        switch (r.charCase) {
                                            case '>':
                                                n = n.toUpperCase();
                                                break;
                                            case '<':
                                                n = n.toLowerCase()
                                        }
                                        break
                                    }
                                u = i + 1
                            }
                            n == this._promptChar && (this._full = !1)
                        }
                        e += n
                    }
                    return e
                }, i.prototype._handleVagueLiterals = function(n) {
                    var i, t, f, r, e, u;
                    if (n.length > this._mskArr.length + 1) return n;
                    if (i = n.length - this._mskArr.length, i != 0 && n.length > 1) {
                        for (t = -1, f = Math.max(0, this._tbx.selectionStart - i), r = f; r < this._mskArr.length; r++)
                            if (this._mskArr[r].vague) {
                                t = r;
                                break
                            }
                        if (t > -1)
                            if (i < 0) e = Array(1 - i).join(this._promptChar), u = t + i, u > -1 && (n = n.substr(0, u) + e + n.substr(u));
                            else {
                                while (t > 0 && this._mskArr[t - 1].literal) t--;
                                n = n.substr(0, t) + n.substr(t + i)
                            }
                    }
                    return n
                }, i.prototype._isCharValid = function(n, t) {
                    var r = this._promptChar;
                    switch (n) {
                        case '0':
                            return t >= '0' && t <= '9' || t == r;
                        case '9':
                            return t >= '0' && t <= '9' || t == ' ' || t == r;
                        case '#':
                            return t >= '0' && t <= '9' || t == ' ' || t == '+' || t == '-' || t == r;
                        case 'L':
                            return t >= 'a' && t <= 'z' || t >= 'A' && t <= 'Z' || t == r;
                        case 'l':
                            return t >= 'a' && t <= 'z' || t >= 'A' && t <= 'Z' || t == ' ' || t == r;
                        case 'A':
                            return t >= '0' && t <= '9' || t >= 'a' && t <= 'z' || t >= 'A' && t <= 'Z' || t == r;
                        case 'a':
                            return t >= '0' && t <= '9' || t >= 'a' && t <= 'z' || t >= 'A' && t <= 'Z' || t == ' ' || t == r;
                        case '\uff19':
                            return t >= '\uFF10' && t <= '\uff19' || t == r;
                        case '\uff2a':
                        case '\uff27':
                            return n == '\uff27' && i._X_DBCS_BIG_HIRA.indexOf(t) > -1 ? !1 : t >= '\u3041' && t <= '\u3096' || t == r;
                        case '\uff2b':
                        case '\uff2e':
                            return n == '\uff2e' && i._X_DBCS_BIG_KATA.indexOf(t) > -1 ? !1 : t >= '\u30a1' && t <= '\u30fa' || t == r;
                        case '\uff3a':
                            return t <= '\u0021' || t >= '\u00ff' || t == r;
                        case 'H':
                            return t >= '\u0021' && t <= '\u00ff' || t == r;
                        case 'K':
                        case 'N':
                            return n == 'N' && i._X_SBCS_BIG_KATA.indexOf(t) > -1 ? !1 : t >= '\uff66' && t <= '\uff9f' || t == r
                    }
                    return !1
                }, i.prototype._validatePosition = function(t) {
                    var i = this._mskArr;
                    if (this._backSpace)
                        while (t > 0 && t < i.length && i[t - 1].literal) t--;
                    if (t == 0 || !this._backSpace)
                        while (t < i.length && i[t].literal) t++;
                    document.activeElement == this._tbx && n.setSelectionRange(this._tbx, t);
                    this._backSpace = !1
                }, i.prototype._parseMask = function() {
                    var r, o, f, i, e, u, s;
                    for (this._mskArr = [], this._firstPos = -1, this._lastPos = -1, r = this._msk, o = '|', i = 0; r && i < r.length; i++) switch (r[i]) {
                        case '0':
                        case '9':
                        case '#':
                        case 'A':
                        case 'a':
                        case 'L':
                        case 'l':
                        case '\uff19':
                        case '\uff2a':
                        case '\uff27':
                        case '\uff2b':
                        case '\uff2e':
                        case '\uff3a':
                        case 'K':
                        case 'N':
                        case 'H':
                            this._firstPos < 0 && (this._firstPos = this._mskArr.length);
                            this._lastPos = this._mskArr.length;
                            this._mskArr.push(new t(r[i], o));
                            break;
                        case '.':
                        case ',':
                        case ':':
                        case '/':
                        case '$':
                            switch (r[i]) {
                                case '.':
                                case ',':
                                    f = n.culture.Globalize.numberFormat[r[i]];
                                    break;
                                case ':':
                                case '/':
                                    f = n.culture.Globalize.calendar[r[i]];
                                    break;
                                case '$':
                                    f = n.culture.Globalize.numberFormat.currency.symbol
                            }
                            for (u = 0; u < f.length; u++) this._mskArr.push(new t(f[u]));
                            break;
                        case '<':
                        case '>':
                        case '|':
                            o = r[i];
                            break;
                        case '\\':
                            i < r.length - 1 && i++;
                            this._mskArr.push(new t(r[i]));
                            break;
                        default:
                            this._mskArr.push(new t(r[i]))
                    }
                    for (i = 0; i < this._mskArr.length; i++)
                        if (e = this._mskArr[i], e.literal)
                            for (u = 0; u < i; u++)
                                if (s = this._mskArr[u], s.wildCard && this._isCharValid(s.wildCard, e.literal)) {
                                    e.vague = !0;
                                    break
                                }
                }, i._X_DBCS_BIG_HIRA = '\u3041\u3043\u3045\u3047\u3049\u3063\u3083\u3085\u3087\u308e\u3095\u3096', i._X_DBCS_BIG_KATA = '\u30a1\u30a3\u30a5\u30a7\u30a9\u30c3\u30e3\u30e5\u30e7\u30ee\u30f5\u30f6', i._X_SBCS_BIG_KATA = '\uff67\uff68\uff69\uff6a\uff6b\uff6c\uff6d\uff6e\uff6f', i
            }(),
            t;
        n._MaskProvider = i;
        t = function() {
            function n(n, t) {
                t ? (this.wildCard = n, this.charCase = t) : this.literal = n
            }
            return n
        }();
        n._MaskElement = t
    }(wijmo || (wijmo = {})),
    function(n) {
        'use strict';

        function r() {
            return i
        }
        var i, t;
        n.isIE9 = r;
        document.doctype && navigator.appVersion.indexOf("MSIE 9") > -1 && (i = !0, document.addEventListener('mousemove', function(n) {
            if (n.which == 1)
                for (var t = n.target; t; t = t.parentNode)
                    if (t.attributes && t.attributes.draggable) return t.dragDrop(), !1
        }));
        window.requestAnimationFrame || (t = 0, window.requestAnimationFrame = function(n) {
            var i = Date.now(),
                r = 16 - (i - t),
                u = r > 0 ? r : 0;
            return t = i + u, setTimeout(function() {
                n(t)
            }, u)
        }, window.cancelAnimationFrame = clearTimeout)
    }(wijmo || (wijmo = {}))