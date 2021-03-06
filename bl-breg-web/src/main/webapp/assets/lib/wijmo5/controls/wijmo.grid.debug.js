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
var __extends = this && this.__extends || function(n, t) {
        function r() {
            this.constructor = n
        }
        for (var i in t) t.hasOwnProperty(i) && (n[i] = t[i]);
        n.prototype = t === null ? Object.create(t) : (r.prototype = t.prototype, new r)
    },
    wijmo;
wijmo.culture.FlexGrid = {
        groupHeaderFormat: '{name}: <b>{value} </b>({count:n0} items)'
    },
    function(n) {
        var t;
        (function(t) {
            'use strict';
            (function(n) {
                n[n.None = 0] = "None";
                n[n.Column = 1] = "Column";
                n[n.Row = 2] = "Row";
                n[n.All = 3] = "All"
            })(t.HeadersVisibility || (t.HeadersVisibility = {}));
            var i = t.HeadersVisibility,
                r = function(r) {
                    function u(u, f) {
                        var e = this,
                            s, h, o;
                        r.call(this, u, null, !0);
                        this._szClient = new n.Size(0, 0);
                        this._ptScrl = new n.Point(0, 0);
                        this._rtl = !1;
                        this._cellPadding = 3;
                        this._autoGenCols = !0;
                        this._autoClipboard = !0;
                        this._readOnly = !1;
                        this._indent = 14;
                        this._autoSizeMode = t.AutoSizeMode.Both;
                        this._hdrVis = i.All;
                        this._alSorting = !0;
                        this._alAddNew = !1;
                        this._alDelete = !1;
                        this._alResizing = t.AllowResizing.Columns;
                        this._alDragging = t.AllowDragging.Columns;
                        this._alMerging = t.AllowMerging.None;
                        this._ssHdr = i.None;
                        this._shSort = !0;
                        this._shGroups = !0;
                        this._shAlt = !0;
                        this._deferResizing = !1;
                        this._pSel = !0;
                        this._pOutline = !0;
                        this.itemsSourceChanged = new n.Event;
                        this.scrollPositionChanged = new n.Event;
                        this.selectionChanging = new n.Event;
                        this.selectionChanged = new n.Event;
                        this.loadingRows = new n.Event;
                        this.loadedRows = new n.Event;
                        this.resizingColumn = new n.Event;
                        this.resizedColumn = new n.Event;
                        this.autoSizingColumn = new n.Event;
                        this.autoSizedColumn = new n.Event;
                        this.draggingColumn = new n.Event;
                        this.draggedColumn = new n.Event;
                        this.resizingRow = new n.Event;
                        this.resizedRow = new n.Event;
                        this.autoSizingRow = new n.Event;
                        this.autoSizedRow = new n.Event;
                        this.draggingRow = new n.Event;
                        this.draggedRow = new n.Event;
                        this.groupCollapsedChanging = new n.Event;
                        this.groupCollapsedChanged = new n.Event;
                        this.sortingColumn = new n.Event;
                        this.sortedColumn = new n.Event;
                        this.beginningEdit = new n.Event;
                        this.prepareCellForEdit = new n.Event;
                        this.cellEditEnding = new n.Event;
                        this.cellEditEnded = new n.Event;
                        this.rowEditEnding = new n.Event;
                        this.rowEditEnded = new n.Event;
                        this.rowAdded = new n.Event;
                        this.deletingRow = new n.Event;
                        this.copying = new n.Event;
                        this.copied = new n.Event;
                        this.pasting = new n.Event;
                        this.pasted = new n.Event;
                        this.pastingCell = new n.Event;
                        this.pastedCell = new n.Event;
                        this.formatItem = new n.Event;
                        this.updatedView = new n.Event;
                        this._mappedColumns = null;
                        s = this.hostElement;
                        navigator.userAgent.match(/MSIE |Trident\/|Edge\//) && (s.style.borderRadius = '0px');
                        h = this.getTemplate();
                        this.applyTemplate('wj-control wj-flexgrid wj-content', h, {
                            _root: 'root',
                            _eSz: 'sz',
                            _eCt: 'cells',
                            _eTL: 'tl',
                            _eCHdr: 'ch',
                            _eRHdr: 'rh',
                            _eTLCt: 'tlcells',
                            _eCHdrCt: 'chcells',
                            _eRHdrCt: 'rhcells',
                            _eMarquee: 'marquee',
                            _eFocus: 'focus'
                        });
                        o = this._getDefaultRowHeight();
                        this.deferUpdate(function() {
                            e._rows = new t.RowCollection(e, o);
                            e._cols = new t.ColumnCollection(e, o * 4);
                            e._hdrRows = new t.RowCollection(e, o);
                            e._hdrCols = new t.ColumnCollection(e, Math.round(o * 1.25));
                            e._gpCells = new t.GridPanel(e, t.CellType.Cell, e._rows, e._cols, e._eCt);
                            e._gpCHdr = new t.GridPanel(e, t.CellType.ColumnHeader, e._hdrRows, e._cols, e._eCHdrCt);
                            e._gpRHdr = new t.GridPanel(e, t.CellType.RowHeader, e._rows, e._hdrCols, e._eRHdrCt);
                            e._gpTL = new t.GridPanel(e, t.CellType.TopLeft, e._hdrRows, e._hdrCols, e._eTLCt);
                            e._hdrRows.push(new t.Row);
                            e._hdrCols.push(new t.Column);
                            e._hdrCols[0].align = 'center';
                            e._cf = new t.CellFactory;
                            e._keyHdl = new t._KeyboardHandler(e);
                            e._mouseHdl = new t._MouseHandler(e);
                            e._edtHdl = new t._EditHandler(e);
                            e._selHdl = new t._SelectionHandler(e);
                            e._addHdl = new t._AddNewHandler(e);
                            e._mrgMgr = new t.MergeManager(e);
                            e._bndSortConverter = e._sortConverter.bind(e);
                            e.initialize(f)
                        });
                        this.addEventListener(this._root, 'scroll', function() {
                            e._raf && cancelAnimationFrame(e._raf);
                            e._raf = requestAnimationFrame(function() {
                                e.finishEditing();
                                e._updateScrollPosition() && e._updateContent(!0);
                                e._raf = null
                            })
                        });
                        this.addEventListener(window, 'scroll', function() {
                            e._updateStickyHeaders()
                        })
                    }
                    return __extends(u, r), u.prototype._handleResize = function() {
                        r.prototype._handleResize.call(this);
                        this._rcBounds = null
                    }, Object.defineProperty(u.prototype, "headersVisibility", {
                        get: function() {
                            return this._hdrVis
                        },
                        set: function(t) {
                            t != this._hdrVis && (this._hdrVis = n.asEnum(t, i), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "stickyHeaders", {
                        get: function() {
                            return this._stickyHdr
                        },
                        set: function(t) {
                            t != this._stickyHdr && (n.removeClass(this._eTL, u._WJS_STICKY), n.removeClass(this._eCHdr, u._WJS_STICKY), this._stickyHdr = n.asBoolean(t), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "preserveSelectedState", {
                        get: function() {
                            return this._pSel
                        },
                        set: function(t) {
                            this._pSel = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "preserveOutlineState", {
                        get: function() {
                            return this._pOutline
                        },
                        set: function(t) {
                            this._pOutline = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "autoGenerateColumns", {
                        get: function() {
                            return this._autoGenCols
                        },
                        set: function(t) {
                            this._autoGenCols = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "autoClipboard", {
                        get: function() {
                            return this._autoClipboard
                        },
                        set: function(t) {
                            this._autoClipboard = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "columnLayout", {
                        get: function() {
                            for (var c, o, r, i, f, s = u._getSerializableProperties(t.Column), l = new t.Column, h = [], e = 0; e < this.columns.length; e++) {
                                for (c = this.columns[e], o = {}, r = 0; r < s.length; r++) i = s[r], f = c[i], f != l[i] && n.isPrimitive(f) && i != 'size' && (o[i] = f);
                                h.push(o)
                            }
                            return JSON.stringify({
                                columns: h
                            })
                        },
                        set: function(t) {
                            var i = JSON.parse(n.asString(t));
                            if (!i || i.columns == null) throw 'Invalid columnLayout data.';
                            this.columns.clear();
                            this.initialize(i)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "isReadOnly", {
                        get: function() {
                            return this._readOnly
                        },
                        set: function(t) {
                            t != this._readOnly && (this._readOnly = n.asBoolean(t), this.finishEditing(), this.invalidate(!0), this._addHdl.updateNewRowTemplate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "imeEnabled", {
                        get: function() {
                            return this._imeHdl != null
                        },
                        set: function(n) {
                            n != this.imeEnabled && (this._imeHdl && (this._imeHdl.dispose(), this._imeHdl = null), n && (this._imeHdl = new t._ImeHandler(this)))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowResizing", {
                        get: function() {
                            return this._alResizing
                        },
                        set: function(i) {
                            this._alResizing = n.asEnum(i, t.AllowResizing)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "deferResizing", {
                        get: function() {
                            return this._deferResizing
                        },
                        set: function(t) {
                            this._deferResizing = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "autoSizeMode", {
                        get: function() {
                            return this._autoSizeMode
                        },
                        set: function(i) {
                            this._autoSizeMode = n.asEnum(i, t.AutoSizeMode)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowSorting", {
                        get: function() {
                            return this._alSorting
                        },
                        set: function(t) {
                            this._alSorting = n.asBoolean(t)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowAddNew", {
                        get: function() {
                            return this._alAddNew
                        },
                        set: function(t) {
                            t != this._alAddNew && (this._alAddNew = n.asBoolean(t), this._addHdl.updateNewRowTemplate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowDelete", {
                        get: function() {
                            return this._alDelete
                        },
                        set: function(t) {
                            t != this._alDelete && (this._alDelete = n.asBoolean(t))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowMerging", {
                        get: function() {
                            return this._alMerging
                        },
                        set: function(i) {
                            i != this._alMerging && (this._alMerging = n.asEnum(i, t.AllowMerging), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "showSelectedHeaders", {
                        get: function() {
                            return this._ssHdr
                        },
                        set: function(t) {
                            t != this._ssHdr && (this._ssHdr = n.asEnum(t, i), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "showMarquee", {
                        get: function() {
                            return !this._eMarquee.style.display
                        },
                        set: function(t) {
                            if (t != this.showMarquee) {
                                var i = this._eMarquee.style;
                                i.visibility = 'collapse';
                                i.display = n.asBoolean(t) ? '' : 'none';
                                this.invalidate()
                            }
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "showSort", {
                        get: function() {
                            return this._shSort
                        },
                        set: function(t) {
                            t != this._shSort && (this._shSort = n.asBoolean(t), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "showGroups", {
                        get: function() {
                            return this._shGroups
                        },
                        set: function(t) {
                            t != this._shGroups && (this._shGroups = n.asBoolean(t), this._bindGrid(!1))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "showAlternatingRows", {
                        get: function() {
                            return this._shAlt
                        },
                        set: function(t) {
                            t != this._shAlt && (this._shAlt = n.asBoolean(t), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "groupHeaderFormat", {
                        get: function() {
                            return this._gHdrFmt
                        },
                        set: function(t) {
                            t != this._gHdrFmt && (this._gHdrFmt = n.asString(t), this._bindGrid(!1))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "allowDragging", {
                        get: function() {
                            return this._alDragging
                        },
                        set: function(i) {
                            i != this._alDragging && (this._alDragging = n.asEnum(i, t.AllowDragging), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "itemsSource", {
                        get: function() {
                            return this._items
                        },
                        set: function(t) {
                            var i;
                            t != this._items && (this._cv && (i = n.tryCast(this._cv, n.collections.CollectionView), i && i.sortConverter == this._bndSortConverter && (i.sortConverter = null), this._cv.currentChanged.removeHandler(this._cvCurrentChanged, this), this._cv.collectionChanged.removeHandler(this._cvCollectionChanged, this), this._cv = null), this._items = t, this._cv = this._getCollectionView(t), this._lastCount = 0, this._cv && (this._cv.currentChanged.addHandler(this._cvCurrentChanged, this), this._cv.collectionChanged.addHandler(this._cvCollectionChanged, this), i = n.tryCast(this._cv, n.collections.CollectionView), i && !i.sortConverter && (i.sortConverter = this._bndSortConverter)), this._bindGrid(!0), this.onItemsSourceChanged())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "collectionView", {
                        get: function() {
                            return this._cv
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "childItemsPath", {
                        get: function() {
                            return this._childItemsPath
                        },
                        set: function(t) {
                            t != this._childItemsPath && (n.assert(t == null || n.isArray(t) || n.isString(t), 'childItemsPath should be an array or a string.'), this._childItemsPath = t, this._bindGrid(!0))
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "cells", {
                        get: function() {
                            return this._gpCells
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "columnHeaders", {
                        get: function() {
                            return this._gpCHdr
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "rowHeaders", {
                        get: function() {
                            return this._gpRHdr
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "topLeftCells", {
                        get: function() {
                            return this._gpTL
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "rows", {
                        get: function() {
                            return this._rows
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "columns", {
                        get: function() {
                            return this._cols
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "frozenRows", {
                        get: function() {
                            return this.rows.frozen
                        },
                        set: function(n) {
                            this.rows.frozen = n
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "frozenColumns", {
                        get: function() {
                            return this.columns.frozen
                        },
                        set: function(n) {
                            this.columns.frozen = n
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "sortRowIndex", {
                        get: function() {
                            return this._sortRowIndex
                        },
                        set: function(t) {
                            t != this._sortRowIndex && (this._sortRowIndex = n.asNumber(t, !0), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "scrollPosition", {
                        get: function() {
                            return this._ptScrl.clone()
                        },
                        set: function(n) {
                            var t = this._root,
                                i = -n.x;
                            if (this._rtl) switch (u._getRtlMode()) {
                                case 'rev':
                                    i = t.scrollWidth - t.clientWidth + n.x;
                                    break;
                                case 'neg':
                                    i = n.x;
                                    break;
                                default:
                                    i = -n.x
                            }
                            t.scrollLeft = i;
                            t.scrollTop = -n.y
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "clientSize", {
                        get: function() {
                            return this._szClient
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "controlRect", {
                        get: function() {
                            return this._rcBounds || (this._rcBounds = n.getElementRect(this._root)), this._rcBounds
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "scrollSize", {
                        get: function() {
                            return new n.Size(this._gpCells.width, this._heightBrowser)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "viewRange", {
                        get: function() {
                            return this._gpCells.viewRange
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "cellFactory", {
                        get: function() {
                            return this._cf
                        },
                        set: function(i) {
                            i != this._cf && (this._cf = n.asType(i, t.CellFactory, !1), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "itemFormatter", {
                        get: function() {
                            return this._itemFormatter
                        },
                        set: function(t) {
                            t != this._itemFormatter && (this._itemFormatter = n.asFunction(t), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), u.prototype.getCellData = function(n, t, i) {
                        return this.cells.getCellData(n, t, i)
                    }, u.prototype.getCellBoundingRect = function(n, t, i) {
                        return this.cells.getCellBoundingRect(n, t, i)
                    }, u.prototype.setCellData = function(n, t, i, r, u) {
                        return r === void 0 && (r = !0), u === void 0 && (u = !0), this.cells.setCellData(n, t, i, r, u)
                    }, u.prototype.hitTest = function(i, r) {
                        return n.isNumber(i) && n.isNumber(r) && (i = new n.Point(i, r)), new t.HitTestInfo(this, i)
                    }, u.prototype.getClipString = function(i) {
                        var f = '',
                            s = !0,
                            o = !0,
                            r, u, e;
                        for (i = i ? n.asType(i, t.CellRange) : this.selection, r = i.topRow; r <= i.bottomRow; r++)
                            if (this.rows[r].isVisible)
                                for (s || (f += '\n'), s = !1, u = i.leftCol, o = !0; u <= i.rightCol; u++) this.columns[u].isVisible && (o || (f += '\t'), o = !1, e = this.cells.getCellData(r, u, !0).toString(), e = e.replace(/\t/g, ' '), f += e);
                        return f
                    }, u.prototype.setClipString = function(i, r) {
                        var v = r == null,
                            e, o, c, u, s;
                        r = r ? n.asType(r, t.CellRange) : this.selection;
                        i = n.asString(i).replace(/\r\n/g, '\n').replace(/\r/g, '\n');
                        i && i[i.length - 1] == '\n' && (i = i.substring(0, i.length - 1));
                        v && !r.isSingleCell && (i = this._expandClipString(i, r));
                        e = new t.CellRange(r.topRow, r.leftCol);
                        this.beginUpdate();
                        var f = r.topRow,
                            l = i.split('\n'),
                            a = !1,
                            h;
                        for (o = 0; o < l.length && f < this.rows.length; o++, f++) {
                            if (!this.rows[f].isVisible) {
                                o--;
                                continue
                            }
                            for (c = l[o].split('\t'), u = r.leftCol, s = 0; s < c.length && u < this.columns.length; s++, u++) {
                                if (!this.columns[u].isVisible) {
                                    s--;
                                    continue
                                }
                                if (!this.columns[u].isReadOnly && !this.rows[f].isReadOnly) {
                                    if (h = new t.CellRangeEventArgs(this.cells, new t.CellRange(f, u), c[s]), this.onPastingCell(h) && this.cells.setCellData(f, u, h.data)) {
                                        this.onPastedCell(h);
                                        a = !0
                                    }
                                    e.row2 = Math.max(e.row2, f);
                                    e.col2 = Math.max(e.col2, u)
                                }
                            }
                        }
                        this.endUpdate();
                        this.collectionView && a && this.collectionView.refresh();
                        this.select(e)
                    }, u.prototype._expandClipString = function(n, t) {
                        var s, r, u, i, f;
                        if (!n) return n;
                        var h = n.split('\n'),
                            o = h.length,
                            e = 0,
                            c = [];
                        for (i = 0; i < o; i++) {
                            if (s = h[i].split('\t'), c.push(s), i > 1 && s.length != e) return n;
                            e = s.length
                        }
                        if (r = t.rowSpan, u = t.columnSpan, (r > 1 || u > 1) && (r == 1 && (r = o), u == 1 && (u = e), u % e == 0 && r % o == 0))
                            for (n = '', i = 0; i < r; i++)
                                for (f = 0; f < u; f++) i > 0 && f == 0 && (n += '\n'), f > 0 && (n += '\t'), n += c[i % o][f % e];
                        return n
                    }, u.prototype.focus = function() {
                        var t, i;
                        if (!this.containsFocus()) {
                            if (t = this.hostElement.getBoundingClientRect(), t.bottom > 0 && t.right > 0 && t.top < innerHeight && t.left < innerWidth) {
                                n.setCss(this._eFocus, {
                                    top: Math.max(0, -t.top),
                                    left: Math.max(0, -t.left)
                                });
                                this._eFocus.focus();
                                n.setCss(this._eFocus, {
                                    top: -10,
                                    left: -10
                                });
                                return
                            }
                            if (i = this.cells.hostElement.querySelector('.wj-cell.wj-state-selected'), i) {
                                i.focus();
                                return
                            }
                            r.prototype.focus.call(this)
                        }
                    }, u.prototype.containsFocus = function() {
                        var n = this._edtHdl._lbx;
                        return r.prototype.containsFocus.call(this) || n && n.containsFocus()
                    }, u.prototype.dispose = function() {
                        this.finishEditing(!0);
                        this.itemsSource = null;
                        this._raf && cancelAnimationFrame(this._raf);
                        r.prototype.dispose.call(this)
                    }, u.prototype.refresh = function(n) {
                        n === void 0 && (n = !0);
                        r.prototype.refresh.call(this, n);
                        this.finishEditing();
                        n && (this._updateColumnTypes(), this.scrollPosition = this._ptScrl);
                        this.refreshCells(n)
                    }, u.prototype.refreshCells = function(n, t, i) {
                        this.isUpdating || (n ? this._updateLayout() : this._updateContent(t, i))
                    }, u.prototype.autoSizeColumn = function(n, t, i) {
                        t === void 0 && (t = !1);
                        i === void 0 && (i = 4);
                        this.autoSizeColumns(n, n, t, i)
                    }, u.prototype.autoSizeColumns = function(i, r, f, e) {
                        var h = this;
                        f === void 0 && (f = !1);
                        e === void 0 && (e = 4);
                        var s = 0,
                            l = f ? this.topLeftCells : this.columnHeaders,
                            o = f ? this.rowHeaders : this.cells,
                            c = this.viewRange,
                            a, v;
                        i = i == null ? 0 : n.asInt(i);
                        r = r == null ? o.columns.length - 1 : n.asInt(r);
                        n.asBoolean(f);
                        n.asNumber(e);
                        c.row = Math.max(0, c.row - 1e3);
                        c.row2 = Math.min(c.row2 + 1e3, this.rows.length - 1);
                        this.deferUpdate(function() {
                            var p = document.createElement('div'),
                                y, n, w;
                            for (p.setAttribute(u._WJS_MEASURE, 'true'), p.style.visibility = 'hidden', h.hostElement.appendChild(p), y = i; y <= r && y > -1 && y < o.columns.length; y++) {
                                if (s = 0, h.autoSizeMode & t.AutoSizeMode.Headers)
                                    for (n = 0; n < l.rows.length; n++) l.rows[n].isVisible && (w = h._getDesiredWidth(l, n, y, p), s = Math.max(s, w));
                                if (h.autoSizeMode & t.AutoSizeMode.Cells)
                                    for (v = null, n = c.row; n <= c.row2 && n > -1 && n < o.rows.length; n++) o.rows[n].isVisible && (!f && y == o.columns.firstVisibleIndex && o.rows.maxGroupLevel > -1 ? (w = h._getDesiredWidth(o, n, y, p), s = Math.max(s, w)) : (a = o.getCellData(n, y, !0), a != v && (v = a, w = h._getDesiredWidth(o, n, y, p), s = Math.max(s, w))));
                                o.columns[y].width = s + e + 2
                            }
                            h.hostElement.removeChild(p)
                        })
                    }, u.prototype.autoSizeRow = function(n, t, i) {
                        t === void 0 && (t = !1);
                        i === void 0 && (i = 0);
                        this.autoSizeRows(n, n, t, i)
                    }, u.prototype.autoSizeRows = function(i, r, f, e) {
                        var o = this;
                        f === void 0 && (f = !1);
                        e === void 0 && (e = 0);
                        var s = 0,
                            c = f ? this.topLeftCells : this.rowHeaders,
                            h = f ? this.columnHeaders : this.cells;
                        f = n.asBoolean(f);
                        e = n.asNumber(e);
                        i = i == null ? 0 : n.asInt(i);
                        r = r == null ? h.rows.length - 1 : n.asInt(r);
                        this.deferUpdate(function() {
                            var l = document.createElement('div'),
                                f, n, a;
                            for (l.setAttribute(u._WJS_MEASURE, 'true'), l.style.visibility = 'hidden', o.hostElement.appendChild(l), f = i; f <= r && f > -1 && f < h.rows.length; f++) {
                                if (s = 0, o.autoSizeMode & t.AutoSizeMode.Headers)
                                    for (n = 0; n < c.columns.length; n++) c.columns[n].renderSize > 0 && (a = o._getDesiredHeight(c, f, n, l), s = Math.max(s, a));
                                if (o.autoSizeMode & t.AutoSizeMode.Cells)
                                    for (n = 0; n < h.columns.length; n++) h.columns[n].renderSize > 0 && (a = o._getDesiredHeight(h, f, n, l), s = Math.max(s, a));
                                h.rows[f].height = s + e
                            }
                            o.hostElement.removeChild(l)
                        })
                    }, Object.defineProperty(u.prototype, "treeIndent", {
                        get: function() {
                            return this._indent
                        },
                        set: function(t) {
                            t != this._indent && (this._indent = n.asNumber(t, !1, !0), this.columns.onCollectionChanged())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), u.prototype.collapseGroupsToLevel = function(i) {
                        if (this.finishEditing()) {
                            var r = this.rows;
                            r.deferUpdate(function() {
                                for (var f, u = 0; u < r.length; u++) f = n.tryCast(r[u], t.GroupRow), f && (f.isCollapsed = f.level >= i)
                            })
                        }
                    }, Object.defineProperty(u.prototype, "selectionMode", {
                        get: function() {
                            return this._selHdl.selectionMode
                        },
                        set: function(i) {
                            i != this.selectionMode && (this._selHdl.selectionMode = n.asEnum(i, t.SelectionMode), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "selection", {
                        get: function() {
                            return this._selHdl.selection.clone()
                        },
                        set: function(n) {
                            this._selHdl.selection = n
                        },
                        enumerable: !0,
                        configurable: !0
                    }), u.prototype.select = function(n, t) {
                        t === void 0 && (t = !0);
                        this._selHdl.select(n, t)
                    }, u.prototype.getSelectedState = function(n, t) {
                        return this.cells.getSelectedState(n, t, null)
                    }, Object.defineProperty(u.prototype, "selectedRows", {
                        get: function() {
                            var i = [],
                                r, n;
                            if (this.selectionMode == t.SelectionMode.ListBox)
                                for (n = 0; n < this.rows.length; n++) this.rows[n].isSelected && i.push(this.rows[n]);
                            else if (this.rows.length)
                                for (r = this.selection, n = r.topRow; n > -1 && n <= r.bottomRow; n++) i.push(this.rows[n]);
                            return i
                        },
                        set: function(i) {
                            var r = this;
                            n.assert(this.selectionMode == t.SelectionMode.ListBox, 'This property can be set only in ListBox mode.');
                            i = n.asArray(i);
                            this.deferUpdate(function() {
                                for (var u, f, n = 0, t = !0; n < r.rows.length; n++) u = r.rows[n], f = i && i.indexOf(u) > -1, f && t && (t = !1, r.select(n, r.selection.col)), u.isSelected = f
                            })
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "selectedItems", {
                        get: function() {
                            for (var n = this.selectedRows, t = 0; t < n.length; t++) n[t] = n[t].dataItem;
                            return n
                        },
                        set: function(i) {
                            var r = this;
                            n.assert(this.selectionMode == t.SelectionMode.ListBox, 'This property can be set only in ListBox mode.');
                            i = n.asArray(i);
                            this.deferUpdate(function() {
                                for (var u, f, n = 0, t = !0; n < r.rows.length; n++) u = r.rows[n], f = i && i.indexOf(u.dataItem) > -1, f && t && (t = !1, r.select(n, r.selection.col)), u.isSelected = f
                            })
                        },
                        enumerable: !0,
                        configurable: !0
                    }), u.prototype.scrollIntoView = function(t, i) {
                        var u;
                        this._maxOffsetY == null && this._updateLayout();
                        var r = this.scrollPosition,
                            h = this._szClient.width,
                            e = this._szClient.height,
                            o = this.cells._getFrozenPos();
                        if (t = n.asInt(t), t > -1 && t < this._rows.length && t >= this._rows.frozen) {
                            var f = this._rows[t],
                                c = this.cells.height > e ? Math.round(f.pos / (this.cells.height - e) * 100) / 100 : 0,
                                l = Math.round(this._maxOffsetY * c),
                                s = f.pos - l;
                            f.pos + f.renderSize > -r.y + e && (r.y = Math.max(-s, e - (f.pos + f.renderSize)));
                            s - o.y < -r.y && (r.y = -(s - o.y))
                        }
                        return (i = n.asInt(i), i > -1 && i < this._cols.length && i >= this._cols.frozen && (u = this._cols[i], u.pos + u.renderSize > -r.x + h && (r.x = Math.max(-u.pos, h - (u.pos + u.renderSize))), u.pos - o.x < -r.x && (r.x = -(u.pos - o.x))), !r.equals(this.scrollPosition)) ? (this.scrollPosition = r, !0) : !1
                    }, u.prototype.isRangeValid = function(n) {
                        return n.isValid && n.bottomRow < this.rows.length && n.rightCol < this.columns.length
                    }, u.prototype.startEditing = function(n, t, i, r) {
                        return n === void 0 && (n = !0), this._edtHdl.startEditing(n, t, i, r)
                    }, u.prototype.finishEditing = function(n) {
                        return n === void 0 && (n = !1), this._edtHdl.finishEditing(n)
                    }, Object.defineProperty(u.prototype, "activeEditor", {
                        get: function() {
                            return this._edtHdl.activeEditor
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "editRange", {
                        get: function() {
                            return this._edtHdl.editRange
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(u.prototype, "mergeManager", {
                        get: function() {
                            return this._mrgMgr
                        },
                        set: function(i) {
                            i != this._mrgMgr && (this._mrgMgr = n.asType(i, t.MergeManager, !0), this.invalidate())
                        },
                        enumerable: !0,
                        configurable: !0
                    }), u.prototype.getMergedRange = function(n, t, i, r) {
                        return r === void 0 && (r = !0), this._mrgMgr ? this._mrgMgr.getMergedRange(n, t, i, r) : null
                    }, u.prototype.onItemsSourceChanged = function(n) {
                        this.itemsSourceChanged.raise(this, n)
                    }, u.prototype.onScrollPositionChanged = function(n) {
                        this.scrollPositionChanged.raise(this, n)
                    }, u.prototype.onSelectionChanging = function(n) {
                        return this.selectionChanging.raise(this, n), !n.cancel
                    }, u.prototype.onSelectionChanged = function(n) {
                        return this.selectionChanged.raise(this, n), !n.cancel
                    }, u.prototype.onLoadingRows = function(n) {
                        return this.loadingRows.raise(this, n), !n.cancel
                    }, u.prototype.onLoadedRows = function(n) {
                        this.loadedRows.raise(this, n)
                    }, u.prototype.onResizingColumn = function(n) {
                        return this.resizingColumn.raise(this, n), !n.cancel
                    }, u.prototype.onResizedColumn = function(n) {
                        this.resizedColumn.raise(this, n)
                    }, u.prototype.onAutoSizingColumn = function(n) {
                        return this.autoSizingColumn.raise(this, n), !n.cancel
                    }, u.prototype.onAutoSizedColumn = function(n) {
                        this.autoSizedColumn.raise(this, n)
                    }, u.prototype.onDraggingColumn = function(n) {
                        return this.draggingColumn.raise(this, n), !n.cancel
                    }, u.prototype.onDraggedColumn = function(n) {
                        this.draggedColumn.raise(this, n)
                    }, u.prototype.onResizingRow = function(n) {
                        return this.resizingRow.raise(this, n), !n.cancel
                    }, u.prototype.onResizedRow = function(n) {
                        this.resizedRow.raise(this, n)
                    }, u.prototype.onAutoSizingRow = function(n) {
                        return this.autoSizingRow.raise(this, n), !n.cancel
                    }, u.prototype.onAutoSizedRow = function(n) {
                        this.autoSizedRow.raise(this, n)
                    }, u.prototype.onDraggingRow = function(n) {
                        return this.draggingRow.raise(this, n), !n.cancel
                    }, u.prototype.onDraggedRow = function(n) {
                        this.draggedRow.raise(this, n)
                    }, u.prototype.onGroupCollapsedChanging = function(n) {
                        return this.groupCollapsedChanging.raise(this, n), !n.cancel
                    }, u.prototype.onGroupCollapsedChanged = function(n) {
                        this.groupCollapsedChanged.raise(this, n)
                    }, u.prototype.onSortingColumn = function(n) {
                        return this.sortingColumn.raise(this, n), !n.cancel
                    }, u.prototype.onSortedColumn = function(n) {
                        this.sortedColumn.raise(this, n)
                    }, u.prototype.onBeginningEdit = function(n) {
                        return this.beginningEdit.raise(this, n), !n.cancel
                    }, u.prototype.onPrepareCellForEdit = function(n) {
                        this.prepareCellForEdit.raise(this, n)
                    }, u.prototype.onCellEditEnding = function(n) {
                        return this.cellEditEnding.raise(this, n), !n.cancel
                    }, u.prototype.onCellEditEnded = function(n) {
                        this.cellEditEnded.raise(this, n)
                    }, u.prototype.onRowEditEnding = function(n) {
                        this.rowEditEnding.raise(this, n)
                    }, u.prototype.onRowEditEnded = function(n) {
                        this.rowEditEnded.raise(this, n)
                    }, u.prototype.onRowAdded = function(n) {
                        this.rowAdded.raise(this, n)
                    }, u.prototype.onDeletingRow = function(n) {
                        this.deletingRow.raise(this, n)
                    }, u.prototype.onCopying = function(n) {
                        return this.copying.raise(this, n), !n.cancel
                    }, u.prototype.onCopied = function(n) {
                        this.copied.raise(this, n)
                    }, u.prototype.onPasting = function(n) {
                        return this.pasting.raise(this, n), !n.cancel
                    }, u.prototype.onPasted = function(n) {
                        this.pasted.raise(this, n)
                    }, u.prototype.onPastingCell = function(n) {
                        return this.pastingCell.raise(this, n), !n.cancel
                    }, u.prototype.onPastedCell = function(n) {
                        this.pastedCell.raise(this, n)
                    }, u.prototype.onFormatItem = function(n) {
                        this.formatItem.raise(this, n)
                    }, u.prototype.onUpdatedView = function(n) {
                        this.updatedView.raise(this, n)
                    }, u.prototype._getDefaultRowHeight = function() {
                        var u = this.hostElement,
                            t = document.body,
                            f = null,
                            i, e, r;
                        if (t && !n.contains(t, u)) {
                            for (i = u; i; i = i.parentElement) f = i;
                            t.appendChild(f)
                        }
                        return e = n.createElement('<div class="wj-cell">123</div>', u), r = e.scrollHeight + 2, u.removeChild(e), f && t.removeChild(f), (r <= 6 || isNaN(r) || !t) && (r = 28), r
                    }, u.prototype._getCollectionView = function(t) {
                        return n.asCollectionView(t)
                    }, u.prototype._getDesiredWidth = function(n, t, i, r) {
                        var u = this.getMergedRange(n, t, i),
                            f;
                        return this.cellFactory.updateCell(n, t, i, r, u), r.style.width = '', f = r.offsetWidth, u && u.columnSpan > 1 ? f / u.columnSpan : f
                    }, u.prototype._getDesiredHeight = function(n, t, i, r) {
                        var u = this.getMergedRange(n, t, i),
                            f;
                        return this.cellFactory.updateCell(n, t, i, r, u), r.style.height = '', f = r.offsetHeight, u && u.rowSpan > 1 ? f / u.rowSpan : f
                    }, u.prototype._getSortRowIndex = function() {
                        return this._sortRowIndex != null ? this._sortRowIndex : this.columnHeaders.rows.length - 1
                    }, u.prototype._sortConverter = function(n, t, i, r) {
                        var u, e, f, o;
                        if (r) {
                            if (this._mappedColumns = null, this.collectionView)
                                for (e = this.collectionView.sortDescriptions, f = 0; f < e.length; f++) u = this.columns.getColumn(e[f].property), u && u.dataMap && (this._mappedColumns || (this._mappedColumns = {}), this._mappedColumns[u.binding] = u.dataMap);
                            this._mouseHdl._htDown && this._mouseHdl._htDown.col > -1 && (u = this.columns[this._mouseHdl._htDown.col], this._mappedColumns && u.dataMap && (this._mappedColumns[u.binding] = u.dataMap))
                        }
                        return this._mappedColumns && (o = this._mappedColumns[n.property], o && (i = o.getDisplayValue(i))), i
                    }, u.prototype._bindGrid = function(i) {
                        var r = this;
                        this.deferUpdate(function() {
                            var o, s, h, c, l, u, e, f;
                            if (r._lastCount == 0 && r._cv && r._cv.items && r._cv.items.length && (i = !0), o = [], r.preserveSelectedState && r.selectionMode == t.SelectionMode.ListBox)
                                for (u = 0; u < r.rows.length; u++) s = r.rows[u], s.isSelected && s.dataItem && o.push(s.dataItem);
                            if (r.preserveOutlineState && n.isFunction(window.Map) && r.rows.maxGroupLevel > -1)
                                for (h = new Map, u = 0; u < r.rows.length; u++) e = r.rows[u], e instanceof t.GroupRow && e.isCollapsed && e.dataItem && (f = e.dataItem, f instanceof n.collections.CollectionViewGroup && (f = f._path), h.set(f, !0));
                            if (i && r.columns.deferUpdate(function() {
                                    r._bindColumns()
                                }), r.rows.deferUpdate(function() {
                                    r._bindRows()
                                }), c = 0, o.length)
                                for (u = 0; u < r.rows.length && c < o.length; u++) o.indexOf(r.rows[u].dataItem) > -1 && (r.rows[u].isSelected = !0, c++);
                            if (r.selectionMode == t.SelectionMode.ListBox && c == 0)
                                for (l = r.selection, u = l.topRow; u <= l.bottomRow && u > -1 && u < r.rows.length; u++) r.rows[u].isSelected = !0;
                            if (h)
                                for (u = 0; u < r.rows.length; u++) e = r.rows[u], e instanceof t.GroupRow && (f = e.dataItem, f instanceof n.collections.CollectionViewGroup && (f = f._path), h.get(f) && (e.isCollapsed = !0));
                            !r._lastCount && r._cv && r._cv.items && (r._lastCount = r._cv.items.length)
                        });
                        this.collectionView && this._cvCurrentChanged(this.collectionView, n.EventArgs.empty)
                    }, u.prototype._cvCollectionChanged = function(i, r) {
                        var u;
                        if (this.autoGenerateColumns && this.columns.length == 0) this._bindGrid(!0);
                        else {
                            if (this.childItemsPath) {
                                r.action == n.collections.NotifyCollectionChangedAction.Change ? this.invalidate() : this._bindGrid(!1);
                                return
                            }
                            switch (r.action) {
                                case n.collections.NotifyCollectionChangedAction.Change:
                                    this.invalidate();
                                    return;
                                case n.collections.NotifyCollectionChangedAction.Add:
                                    if (r.index == this.collectionView.items.length - 1) {
                                        u = this.rows.length;
                                        this.rows[u - 1] instanceof t._NewRowTemplate && u--;
                                        this.rows.insert(u, new t.Row(r.item));
                                        return
                                    }
                                    n.assert(!1, 'added item should be the last one.');
                                    break;
                                case n.collections.NotifyCollectionChangedAction.Remove:
                                    if (u = this._findRow(r.item), u > -1) {
                                        this.rows.removeAt(u);
                                        this._cvCurrentChanged(i, r);
                                        return
                                    }
                                    n.assert(!1, 'removed item not found in grid.')
                            }
                            this._bindGrid(!1)
                        }
                    }, u.prototype._cvCurrentChanged = function() {
                        if (this.collectionView) {
                            var i = this.selection,
                                r = i.row > -1 && i.row < this.rows.length ? this.rows[i.row].dataItem : null;
                            r instanceof n.collections.CollectionViewGroup && (r = null);
                            r != this.collectionView.currentItem && (i.row = i.row2 = this._getRowIndex(this.collectionView.currentPosition), this.select(i, !1), this.selectionMode != t.SelectionMode.None && this.scrollIntoView(i.row, -1))
                        }
                    }, u.prototype._getRowIndex = function(n) {
                        var r, n, i;
                        if (this.collectionView) {
                            if (n > -1) {
                                for (r = this.collectionView.items[n]; n < this.rows.length; n++)
                                    if (this.rows[n].dataItem === r) return n;
                                return -1
                            }
                            return this.rows.length == 1 && this.rows[0] instanceof t._NewRowTemplate ? 0 : (n = this.selection.row, i = n > -1 ? this.rows[n] : null, i && (i instanceof t.GroupRow || i.dataItem == null) ? n : -1)
                        }
                        return this.selection.row
                    }, u.prototype._getCvIndex = function(n) {
                        if (n > -1 && this.collectionView) {
                            var t = this.rows[n].dataItem;
                            for (n = Math.min(n, this.collectionView.items.length); n > -1; n--)
                                if (this.collectionView.items[n] === t) return n
                        }
                        return -1
                    }, u.prototype._findRow = function(n) {
                        for (var t = 0; t < this.rows.length; t++)
                            if (this.rows[t].dataItem == n) return t;
                        return -1
                    }, u.prototype._updateLayout = function() {
                        var f = this._rows.getTotalSize(),
                            t = this._hdrVis & i.Row ? this._hdrCols.getTotalSize() : 0,
                            r = this._hdrVis & i.Column ? this._hdrRows.getTotalSize() : 0,
                            e, c, o, s, h;
                        f < 1 && (f = 1);
                        this._rtl = this.hostElement ? getComputedStyle(this.hostElement).direction == 'rtl' : !1;
                        this._heightBrowser = Math.min(f, u._getMaxSupportedCssHeight());
                        this._maxOffsetY = Math.max(0, f - this._heightBrowser);
                        this.cells.hostElement && (e = n.createElement('<div class="wj-cell"></div>', this.cells.hostElement), c = getComputedStyle(e), this._cellPadding = parseInt(this._rtl ? c.paddingRight : c.paddingLeft), e.parentElement.removeChild(e));
                        this._rtl ? (n.setCss(this._eTL, {
                            right: 0,
                            top: 0,
                            width: t,
                            height: r
                        }), n.setCss(this._eCHdr, {
                            top: 0,
                            right: t,
                            height: r
                        }), n.setCss(this._eRHdr, {
                            right: 0,
                            top: r,
                            width: t
                        }), n.setCss(this._eCt, {
                            right: t,
                            top: r,
                            width: this._gpCells.width,
                            height: this._heightBrowser
                        })) : (n.setCss(this._eTL, {
                            left: 0,
                            top: 0,
                            width: t,
                            height: r
                        }), n.setCss(this._eCHdr, {
                            top: 0,
                            left: t,
                            height: r
                        }), n.setCss(this._eRHdr, {
                            left: 0,
                            top: r,
                            width: t
                        }), n.setCss(this._eCt, {
                            left: t,
                            top: r,
                            width: this._gpCells.width,
                            height: this._heightBrowser
                        }));
                        this._updateStickyHeaders();
                        o = this._root.offsetWidth - this._root.clientWidth;
                        s = this._root.offsetHeight - this._root.clientHeight;
                        n.setCss(this._eSz, {
                            width: t + o + this._gpCells.width,
                            height: r + s + this._heightBrowser
                        });
                        h = null;
                        this.columns._updateStarSizes(this._root.clientWidth - t) && (h = this._root.clientWidth, this._eCt.style.width = this._gpCells.width + 'px');
                        this._szClient = new n.Size(this._root.clientWidth - t, this._root.clientHeight - r);
                        this._rcBounds = null;
                        this._updateContent(!1);
                        o = this._root.offsetWidth - this._root.clientWidth;
                        s = this._root.offsetHeight - this._root.clientHeight;
                        n.setCss(this._eSz, {
                            width: t + o + this._gpCells.width,
                            height: r + s + this._heightBrowser
                        });
                        this._szClient = new n.Size(this._root.clientWidth - t, this._root.clientHeight - r);
                        h && h != this._root.clientWidth && this.columns._updateStarSizes(this._root.clientWidth - t) && (this._eCt.style.width = this._gpCells.width + 'px', this._updateContent(!1));
                        this._eCHdr.style.width = this._szClient.width + 'px';
                        this._eRHdr.style.height = this._szClient.height + 'px'
                    }, u.prototype._updateStickyHeaders = function() {
                        if (this._stickyHdr) {
                            var t = this._root ? Math.min(0, this._root.getBoundingClientRect().top) : 0,
                                i = t != 0;
                            this._eTL.style.top = this._eCHdr.style.top = -t + 'px';
                            n.toggleClass(this._eTL, u._WJS_STICKY, i);
                            n.toggleClass(this._eCHdr, u._WJS_STICKY, i)
                        }
                    }, u.prototype._updateScrollPosition = function() {
                        var t = this._root,
                            f = t.scrollTop,
                            i = t.scrollLeft,
                            r;
                        return (this._rtl && u._getRtlMode() == 'rev' && (i = t.scrollWidth - t.clientWidth - i), r = new n.Point(-Math.abs(i), -f), this._ptScrl.equals(r)) ? !1 : (this._ptScrl = r, this.onScrollPositionChanged(), !0)
                    }, u.prototype._updateContent = function(t, r) {
                        var l = this,
                            a = this.containsFocus(),
                            v = n.contains(this.columnHeaders.hostElement, document.activeElement),
                            o = 1,
                            e, u;
                        if (this._heightBrowser > this._szClient.height && (o = Math.round(-this._ptScrl.y / (this._heightBrowser - this._szClient.height) * 100) / 100), this._offsetY = Math.round(this._maxOffsetY * o), this._gpCells._updateContent(t, r, this._offsetY), n.isIE9() && this._updateScrollPosition(), this._hdrVis & i.Column && (!r || this._ssHdr & i.Column) && this._gpCHdr._updateContent(t, r, 0), this._hdrVis & i.Row && (!r || this._ssHdr & i.Row) && this._gpRHdr._updateContent(t, r, this._offsetY), this._hdrVis && !r && this._gpTL._updateContent(t, r, 0), this.showMarquee)
                            if (e = this._selHdl._sel, u = this._eMarquee, this.isRangeValid(e)) {
                                var f = this._getMarqueeRect(e),
                                    s = u.firstChild,
                                    h = u.offsetWidth - s.offsetWidth,
                                    c = u.offsetHeight - s.offsetHeight;
                                n.setCss(u, {
                                    left: f.left + this.cells.hostElement.offsetLeft - h / 2,
                                    top: f.top + this.cells.hostElement.offsetTop - c / 2,
                                    width: f.width + h,
                                    height: f.height + c,
                                    visibility: f.width > 0 && f.height > 0 ? '' : 'collapse'
                                })
                            } else n.setCss(u, {
                                left: 0,
                                top: 0,
                                width: 0,
                                height: 0,
                                visibility: 'collapse'
                            });
                        a && !r && setTimeout(function() {
                            l.focus()
                        }, 10);
                        this._rcBounds = null;
                        this.onUpdatedView()
                    }, u.prototype._getMarqueeRect = function(i) {
                        var s = this.getMergedRange(this.cells, i.topRow, i.leftCol) || new t.CellRange(i.topRow, i.leftCol),
                            h = this.getMergedRange(this.cells, i.bottomRow, i.rightCol) || new t.CellRange(i.bottomRow, i.rightCol),
                            u = this.cells.getCellBoundingRect(s.topRow, s.leftCol, !0),
                            f = this.cells.getCellBoundingRect(h.bottomRow, h.rightCol, !0),
                            e, o, r;
                        return this.rows.frozen && (e = Math.min(this.rows.length, this.rows.frozen), r = this.cells.getCellBoundingRect(e - 1, 0, !0), i.topRow >= e && u.top < r.bottom && (u.top = r.bottom), i.bottomRow >= e && f.bottom < r.bottom && (f.height = r.bottom - f.top)), this.columns.frozen && (o = Math.min(this.columns.length, this.columns.frozen), r = this.cells.getCellBoundingRect(0, o - 1, !0), i.leftCol >= o && u.left < r.right && (u.left = r.right), i.rightCol >= o && f.right < r.right && (f.width = r.right - f.left)), new n.Rect(u.left, u.top, f.right - u.left, f.bottom - u.top)
                    }, u.prototype._bindColumns = function() {
                        for (var i, r, e, f, o, u = 0; u < this.columns.length; u++) i = this.columns[u], i._getFlag(t.RowColFlags.AutoGenerated) && (this.columns.removeAt(u), u--);
                        if (r = null, e = this.collectionView, e && e.sourceCollection && e.sourceCollection.length && (r = e.sourceCollection[0]), r && this.autoGenerateColumns)
                            for (f in r) n.isPrimitive(r[f]) && (i = new t.Column, i._setFlag(t.RowColFlags.AutoGenerated, !0), i.binding = i.name = f, i.header = n.toHeaderCase(f), i.dataType = n.getType(r[f]), o = Object.getOwnPropertyDescriptor(r, f), o && !o.writable && i._setFlag(t.RowColFlags.ReadOnly, !0), i.dataType == n.DataType.Number && (i.width = 80), this.columns.push(i));
                        this._updateColumnTypes()
                    }, u.prototype._updateColumnTypes = function() {
                        var u = this.collectionView,
                            f, r, i, t;
                        if (n.hasItems(u))
                            for (f = u.items[0], r = this.columns, i = 0; i < r.length; i++) t = r[i], t.dataType == null && t._binding && (t.dataType = n.getType(t._binding.getValue(f)))
                    }, u.prototype._getBindingColumn = function(n, t, i) {
                        return i
                    }, u.prototype._bindRows = function() {
                        var f = new n.CancelEventArgs,
                            r, u, i;
                        this.onLoadingRows(f);
                        if (!f.cancel) {
                            if (this.rows.clear(), this.collectionView && this.collectionView.items)
                                if (r = this.collectionView.items, u = this.collectionView.groups, this.childItemsPath)
                                    for (i = 0; i < r.length; i++) this._addTreeNode(r[i], 0);
                                else if (u != null && u.length > 0 && this.showGroups)
                                for (i = 0; i < u.length; i++) this._addGroup(u[i]);
                            else
                                for (i = 0; i < r.length; i++) this.rows.push(new t.Row(r[i]));
                            this.onLoadedRows(f)
                        }
                    }, u.prototype._addGroup = function(n) {
                        var r = new t.GroupRow,
                            u, i;
                        if (r.level = n.level, r.dataItem = n, this.rows.push(r), n.isBottomLevel)
                            for (i = 0; i < n.items.length; i++) u = new t.Row(n.items[i]), this.rows.push(u);
                        else
                            for (i = 0; i < n.groups.length; i++) this._addGroup(n.groups[i])
                    }, u.prototype._addTreeNode = function(i, r) {
                        var f = new t.GroupRow,
                            e = this.childItemsPath,
                            s = n.isArray(e) ? e[r] : e,
                            o = i[s],
                            u;
                        if (f.dataItem = i, f.level = r, this.rows.push(f), o)
                            for (u = 0; u < o.length; u++) this._addTreeNode(o[u], r + 1)
                    }, u._getSerializableProperties = function(n) {
                        var f = [],
                            u, t, i, r;
                        for (n = n.prototype; n; n = Object.getPrototypeOf(n))
                            for (u = Object.getOwnPropertyNames(n), t = 0; t < u.length; t++) i = u[t], r = Object.getOwnPropertyDescriptor(n, i), r && r.set && r.get && i[0] != '_' && f.push(i);
                        return f
                    }, u.prototype._copy = function(i, r) {
                        var f, u, e;
                        if (i == 'columns') {
                            for (this.columns.clear(), f = n.asArray(r), u = 0; u < f.length; u++) e = new t.Column, n.copy(e, f[u]), this.columns.push(e);
                            return !0
                        }
                        return !1
                    }, u._getMaxSupportedCssHeight = function() {
                        var n;
                        if (!u._maxCssHeight) {
                            var i = 1e6,
                                t = document.createElement('div');
                            for (t.style.visibility = 'hidden', document.body.appendChild(t), n = i; n <= 6e7; n += 5e5) {
                                if (t.style.height = n + 'px', t.offsetHeight != n) break;
                                i = n
                            }
                            document.body.removeChild(t);
                            u._maxCssHeight = i
                        }
                        return u._maxCssHeight
                    }, u._getRtlMode = function() {
                        var t, i, r;
                        return u._rtlMode || (t = n.createElement('<div dir="rtl" style="visibility:hidden;width:100px;height:100px;overflow:auto"><div style="width:2000px;height:2000px"><\/div><\/div>'), document.body.appendChild(t), i = t.scrollLeft, t.scrollLeft = -1e3, r = t.scrollLeft, document.body.removeChild(t), u._rtlMode = r < 0 ? 'neg' : i > 0 ? 'rev' : 'std'), u._rtlMode
                    }, u._WJS_STICKY = 'wj-state-sticky', u._WJS_MEASURE = 'wj-state-measuring', u.controlTemplate = '<div style="position:relative;width:100%;height:100%;overflow:hidden;max-width:inherit;max-height:inherit"><div wj-part="focus" tabIndex="0" style="position:absolute;left:-10px;top:-10px"><\/div><div wj-part="root" style="position:absolute;width:100%;height:100%;overflow:auto;-webkit-overflow-scrolling:touch;max-width:inherit;max-height:inherit;boxSizing:content-box"><div wj-part="cells" class="wj-cells" style="position:relative"><\/div><div wj-part="marquee" class="wj-marquee" style="display:none;pointer-events:none"><div style="width:100%;height:100%"><\/div><\/div><\/div><div wj-part="rh" style="position:absolute;overflow:hidden;outline:none"><div wj-part="rhcells" class="wj-rowheaders" style="position:relative"><\/div><\/div><div wj-part="ch" style="position:absolute;overflow:hidden;outline:none"><div wj-part="chcells" class="wj-colheaders" style="position:relative"><\/div><\/div><div wj-part="tl" style="position:absolute;overflow:hidden;outline:none"><div wj-part="tlcells" class="wj-topleft" style="position:relative"><\/div><\/div><div wj-part="sz" style="position:relative;visibility:hidden;"><\/div><\/div>', u
                }(n.Control);
            t.FlexGrid = r
        })(t = n.grid || (n.grid = {}))
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
            var i = function(i) {
                    function r(r, u, f) {
                        i.call(this);
                        this._p = n.asType(r, t.GridPanel);
                        this._rng = n.asType(u, t.CellRange);
                        this._data = f
                    }
                    return __extends(r, i), Object.defineProperty(r.prototype, "panel", {
                        get: function() {
                            return this._p
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "range", {
                        get: function() {
                            return this._rng.clone()
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "row", {
                        get: function() {
                            return this._rng.row
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "col", {
                        get: function() {
                            return this._rng.col
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "data", {
                        get: function() {
                            return this._data
                        },
                        set: function(n) {
                            this._data = n
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r
                }(n.CancelEventArgs),
                r;
            t.CellRangeEventArgs = i;
            r = function(t) {
                function i(i, r, u) {
                    t.call(this, i, r);
                    this._cell = n.asType(u, HTMLElement)
                }
                return __extends(i, t), Object.defineProperty(i.prototype, "cell", {
                    get: function() {
                        return this._cell
                    },
                    enumerable: !0,
                    configurable: !0
                }), i
            }(i);
            t.FormatItemEventArgs = r
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            (function(n) {
                n[n.None = 0] = "None";
                n[n.Cell = 1] = "Cell";
                n[n.ColumnHeader = 2] = "ColumnHeader";
                n[n.RowHeader = 3] = "RowHeader";
                n[n.TopLeft = 4] = "TopLeft"
            })(t.CellType || (t.CellType = {}));
            var i = t.CellType,
                r = function() {
                    function r(i, u, f, e, o) {
                        this._offsetY = 0;
                        this._g = n.asType(i, t.FlexGrid);
                        this._ct = n.asInt(u);
                        this._rows = n.asType(f, t.RowCollection);
                        this._cols = n.asType(e, t.ColumnCollection);
                        this._e = n.asType(o, HTMLElement);
                        this._rng = new t.CellRange;
                        r._evtBlur || (r._evtBlur = document.createEvent('HTMLEvents'), r._evtBlur.initEvent('blur', !0, !1))
                    }
                    return Object.defineProperty(r.prototype, "grid", {
                        get: function() {
                            return this._g
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "cellType", {
                        get: function() {
                            return this._ct
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "viewRange", {
                        get: function() {
                            return this._getViewRange(!1)
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "width", {
                        get: function() {
                            return this._cols.getTotalSize()
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "height", {
                        get: function() {
                            return this._rows.getTotalSize()
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "rows", {
                        get: function() {
                            return this._rows
                        },
                        enumerable: !0,
                        configurable: !0
                    }), Object.defineProperty(r.prototype, "columns", {
                        get: function() {
                            return this._cols
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype.getCellData = function(r, u, f) {
                        var s = this._rows[n.asNumber(r, !1, !0)],
                            h, e = null,
                            o, c;
                        if (n.isString(u) && (u = this._cols.indexOf(u), u < 0)) throw 'Invalid column name or binding.';
                        if (h = this._cols[n.asNumber(u, !1, !0)], o = this._g ? this._g._getBindingColumn(this, r, h) : h, !o.binding || !s.dataItem || s.dataItem instanceof n.collections.CollectionViewGroup ? s._ubv && (e = s._ubv[h._hash]) : e = o._binding.getValue(s.dataItem), e == null) switch (this._ct) {
                            case i.ColumnHeader:
                                (r == this._rows.length - 1 || o != h) && (e = o.header);
                                break;
                            case i.Cell:
                                h.aggregate != n.Aggregate.None && s instanceof t.GroupRow && (c = n.tryCast(s.dataItem, n.collections.CollectionViewGroup), c && (e = c.getAggregate(o.aggregate, o.binding, this._g.collectionView)))
                        }
                        return f && (this.cellType == i.Cell && o.dataMap && (e = o.dataMap.getDisplayValue(e)), e = e != null ? n.Globalize.format(e, o.format) : ''), e
                    }, r.prototype.setCellData = function(t, r, u, f, e) {
                        var s, a, o, c, y, h, p, l;
                        if (f === void 0 && (f = !0), e === void 0 && (e = !0), s = this._rows[n.asNumber(t, !1, !0)], n.isString(r) && (r = this._cols.indexOf(r), r < 0)) throw 'Invalid column name or binding.';
                        if (a = this._cols[n.asNumber(r, !1, !0)], o = this._g ? this._g._getBindingColumn(this, t, a) : a, this._ct == i.Cell) {
                            if (o.dataMap && u != null && (o.required || u != '' && u != null))
                                if (c = o.dataMap, y = c.getKeyValue(u), y == null) {
                                    if (!c.isEditable || c.displayMemberPath != c.selectedValuePath) return !1
                                } else u = y;
                            if (h = n.DataType.Object, o.dataType ? h = o.dataType : (p = this.getCellData(t, r, !1), h = n.getType(p)), n.isBoolean(o.required))
                                if (o.required || u !== '' && u !== null) {
                                    if (o.required && (u === '' || u === null)) return !1
                                } else u = null, f = !1;
                            if (f && (u = n.changeType(u, h, o.format), h != n.DataType.Object && n.getType(u) != h)) return !1
                        }
                        if (s.dataItem && o.binding) {
                            var w = o._binding,
                                v = s.dataItem,
                                b = w.getValue(v);
                            u === b || n.DateTime.equals(u, b) || (w.setValue(v, u), l = this._g.collectionView, l instanceof n.collections.CollectionView && l.trackChanges && v != l.currentEditItem && l._trackItemChanged(v))
                        } else s._ubv || (s._ubv = {}), s._ubv[a._hash] = u;
                        return e && this._g && this._g.invalidate(), !0
                    }, r.prototype.getCellBoundingRect = function(t, i, r) {
                        var e = this.rows[t],
                            o = this.columns[i],
                            u = new n.Rect(o.pos, e.pos, o.renderSize, e.renderSize),
                            f;
                        return this._g._rtl && (u.left = this.hostElement.offsetWidth - u.right), r || (f = this.hostElement.getBoundingClientRect(), u.left += f.left, u.top += f.top - this._offsetY), t < this.rows.frozen && (u.top -= this._g.scrollPosition.y), i < this.columns.frozen && (u.left -= this._g.scrollPosition.x), u
                    }, r.prototype.getSelectedState = function(n, r, u) {
                        var e = this._g,
                            o = e.selectionMode,
                            f = e._selHdl._sel;
                        if (o != t.SelectionMode.None) switch (this._ct) {
                            case i.Cell:
                                if (u || (u = e.getMergedRange(this, n, r)), u) {
                                    if (u.contains(f.row, f.col)) return e.showMarquee ? t.SelectedState.None : t.SelectedState.Cursor;
                                    if (u.intersects(f)) return t.SelectedState.Selected
                                }
                                return f.row == n && f.col == r ? e.showMarquee ? t.SelectedState.None : t.SelectedState.Cursor : e.rows[n].isSelected || e.columns[r].isSelected ? t.SelectedState.Selected : (f = e._selHdl._adjustSelection(f), o == t.SelectionMode.ListBox) ? t.SelectedState.None : f.containsRow(n) && f.containsColumn(r) ? t.SelectedState.Selected : t.SelectedState.None;
                            case i.ColumnHeader:
                                if (e.showSelectedHeaders & t.HeadersVisibility.Column && (e.columns[r].isSelected || f.containsColumn(r) || f.intersectsColumn(u)) && (u && (n = u.bottomRow), n == this.rows.length - 1)) return t.SelectedState.Selected;
                                break;
                            case i.RowHeader:
                                if (e.showSelectedHeaders & t.HeadersVisibility.Row && (e.rows[n].isSelected || f.containsRow(n) || f.intersectsRow(u)) && (u && (r = u.rightCol), r == this.columns.length - 1)) return t.SelectedState.Selected
                        }
                        return t.SelectedState.None
                    }, Object.defineProperty(r.prototype, "hostElement", {
                        get: function() {
                            return this._e
                        },
                        enumerable: !0,
                        configurable: !0
                    }), r.prototype._getOffsetY = function() {
                        return this._offsetY
                    }, r.prototype._updateContent = function(t, u, f) {
                        var e, s, w, v = this._g,
                            c = this._rows,
                            b = this._cols,
                            l, a, y, o, p, k, d, h;
                        if ((this._ct == i.ColumnHeader || this._ct == i.RowHeader) && (l = v._ptScrl, a = this._e.style, this.cellType == i.ColumnHeader ? v._rtl ? a.right = l.x + 'px' : a.left = l.x + 'px' : this.cellType == i.RowHeader && (a.top = l.y + 'px')), this._offsetY != f && (t = !1, this._offsetY = f), y = this._getViewRange(!1), o = t && v.isTouching ? this._getViewRange(!0) : y, !t || u || c.frozen || b.frozen || !this._rng.contains(y)) {
                            if (t && o.equals(this._rng) || (u = !1), !t) {
                                for (p = n.contains(this._e, document.activeElement) ? document.activeElement : null, k = this._g.cellFactory, h = 0; h < this._e.childElementCount; h++) k.disposeCell(this._e.children[h]);
                                n.setText(this._e, null);
                                p && p.dispatchEvent(r._evtBlur)
                            }
                            for (!t || this._ct != i.Cell || c.frozen || b.frozen || this._reorderCells(o, this._rng), this._rng = o, s = 0, e = o.topRow; e <= o.bottomRow && e > -1; e++) s = this._renderRow(e, o, !1, u, s);
                            for (e = o.topRow; e <= o.bottomRow && e > -1; e++) s = this._renderRow(e, o, !0, u, s);
                            for (e = 0; e < c.frozen && e < c.length; e++) s = this._renderRow(e, o, !1, u, s);
                            for (e = 0; e < c.frozen && e < c.length; e++) s = this._renderRow(e, o, !0, u, s);
                            for (d = this._e.childElementCount, h = s; h < d; h++) w = this._e.children[h], w.style.display = 'none'
                        }
                    }, r.prototype._reorderCells = function(n, t) {
                        var h = Math.max(1, n.rowSpan - 1),
                            r = n.row > -1 && t.row > -1 && n.intersects(t) ? n.row - t.row : 0,
                            i, f, e;
                        if (r > 0 && r <= h) {
                            for (var s = this._g.rows[n.row], c = s.pos, u = 0; u < this._e.childElementCount; u++)
                                if (f = this._e.children[u], e = parseInt(f.style.top), e >= c) break;
                            i = document.createRange();
                            i.setStart(this._e, 0);
                            i.setEnd(this._e, u);
                            this._e.appendChild(i.extractContents())
                        }
                        if (r < 0 && r >= -h) {
                            for (var s = this._g.rows[n.row2], l = s.pos + s.renderSize, o = this._e.childElementCount - 1; o >= 0; o--)
                                if (f = this._e.children[o], e = parseInt(f.style.top), e < l) break;
                            i = document.createRange();
                            i.setStart(this._e, o + 1);
                            i.setEnd(this._e, this._e.childElementCount);
                            this._e.insertBefore(i.extractContents(), this._e.firstChild)
                        }
                    }, r.prototype._renderRow = function(n, t, i, r, u) {
                        var f;
                        if (this.rows[n].renderSize <= 0) return u;
                        if (i)
                            for (f = 0; f < this.columns.frozen && f < this.columns.length; f++) u = this._renderCell(n, f, t, r, u);
                        else
                            for (f = t.leftCol; f <= t.rightCol && f > -1; f++) u = this._renderCell(n, f, t, r, u);
                        return u
                    }, r.prototype._renderCell = function(i, r, u, f, e) {
                        var l = this._g,
                            h = l.getMergedRange(this, i, r),
                            s, o, c;
                        if (h) {
                            for (s = Math.max(u.row, h.row); s < i; s++)
                                if (this.rows[s].isVisible) return e;
                            for (s = Math.max(u.col, h.col); s < r; s++)
                                if (this.columns[s].isVisible) return e
                        }
                        return this.columns[r].renderSize <= 0 && (!h || h.getRenderSize(this).width <= 0) ? e : (o = this._e.childNodes[e++], o && f) ? (c = this.getSelectedState(i, r, h), n.toggleClass(o, 'wj-state-selected', c == t.SelectedState.Cursor), n.toggleClass(o, 'wj-state-multi-selected', c == t.SelectedState.Selected), e) : (o || (o = document.createElement('div'), o.tabIndex = 0, this._e.appendChild(o)), l.cellFactory.updateCell(this, i, r, o, h), e)
                    }, r.prototype._getViewRange = function(n) {
                        var h = this._g,
                            a = h._ptScrl,
                            u = this._rows,
                            f = this._cols,
                            r = new t.CellRange(0, 0, u.length - 1, f.length - 1),
                            e, o;
                        if (this._ct == i.Cell || this._ct == i.RowHeader) {
                            var c = -a.y + this._offsetY,
                                v = h.clientSize.height + 1,
                                s = Math.min(u.frozen, u.length - 1);
                            s > 0 && (e = u[s - 1].pos, c += e, v -= e);
                            r.row = Math.min(u.length - 1, Math.max(u.frozen, u.getItemAt(c)));
                            r.row2 = u.getItemAt(c + v)
                        }
                        if (this._ct == i.Cell || this._ct == i.ColumnHeader) {
                            var l = -a.x,
                                y = h.clientSize.width + 1,
                                s = Math.min(f.frozen, f.length - 1);
                            s > 0 && (e = f[s - 1].pos, l += e, y -= e);
                            r.col = Math.min(f.length - 1, Math.max(f.frozen, f.getItemAt(l)));
                            r.col2 = f.getItemAt(l + y)
                        }
                        return !n || this._ct != i.Cell || u.frozen || f.frozen || (o = 6, r.row < this._rng.row && (r.row = Math.max(r.row - o, 0)), r.row2 > this._rng.row2 && (r.row2 = Math.min(r.row2 + o, u.length - 1)), r.col < this._rng.col && (r.col = Math.max(r.col - o, 0)), r.col2 > this._rng.col2 && (r.col2 = Math.min(r.col2 + o, f.length - 1))), u.length <= u.frozen && (r.row = r.row2 = -1), f.length <= f.frozen && (r.col = r.col2 = -1), r
                    }, r.prototype._getFrozenPos = function() {
                        var r = this._rows.frozen,
                            u = this._cols.frozen,
                            t = r > 0 ? this._rows[r - 1] : null,
                            i = u > 0 ? this._cols[u - 1] : null,
                            f = t ? t.pos + t.renderSize : 0,
                            e = i ? i.pos + i.renderSize : 0;
                        return new n.Point(e, f)
                    }, r
                }();
            t.GridPanel = r
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function i() {}
                return i.prototype.updateCell = function(r, u, f, e, o, s) {
                    var h = r.grid,
                        p = r.cellType,
                        g = r.rows,
                        nt = r.columns,
                        y = g[u],
                        k = nt[f],
                        tt = u,
                        ut = f,
                        w = n.tryCast(y, t.GroupRow),
                        pt = n.tryCast(y, t._NewRowTemplate),
                        wt = k.renderWidth,
                        bt = y.renderHeight,
                        a = 'wj-cell',
                        l = {
                            display: ''
                        },
                        kt = s != !1,
                        ht, ft, d, gt, lt, et, v, at, vt, b, yt, it, ot, rt, ii, ni, st, o, ti;
                    s != !1 && e.firstElementChild && (e.childNodes.length != 1 || e.firstElementChild.type != 'checkbox') && (n.setText(e, null), kt = !1);
                    o && !o.isSingleCell && (u = o.row, f = o.col, tt = o.row2, ut = o.col2, y = g[u], k = nt[f], w = n.tryCast(y, t.GroupRow), ht = o.getRenderSize(r), bt = ht.height, wt = ht.width);
                    var c = h._getBindingColumn(r, u, k),
                        ct = k.pos,
                        dt = y.pos;
                    u < g.frozen && (dt -= h._ptScrl.y);
                    f < nt.frozen && (ct -= h._ptScrl.x);
                    h._rtl ? l.right = ct + 'px' : l.left = ct + 'px';
                    l.top = dt - r._getOffsetY() + 'px';
                    l.width = wt + 'px';
                    l.height = bt + 'px';
                    p == t.CellType.Cell ? (w && (a += ' wj-group'), u % 2 != 0 && h.showAlternatingRows && (a += ' wj-alt'), (u < g.frozen || f < nt.frozen) && (a += ' wj-frozen'), pt && (a += ' wj-new'), y.cssClass && (a += ' ' + y.cssClass), k.cssClass && (a += ' ' + k.cssClass)) : a += ' wj-header';
                    ft = r.getSelectedState(u, f, o);
                    ft != t.SelectedState.None && p == t.CellType.Cell && k.dataType != n.DataType.Boolean && h.editRange && h.editRange.contains(u, f) && (ft = t.SelectedState.None);
                    switch (ft) {
                        case t.SelectedState.Cursor:
                            a += ' wj-state-selected';
                            break;
                        case t.SelectedState.Selected:
                            a += ' wj-state-multi-selected'
                    }
                    if (tt == g.frozen - 1 && (a += ' wj-frozen-row'), ut == nt.frozen - 1 && (a += ' wj-frozen-col'), (k.wordWrap || y.wordWrap) && (a += ' wj-wrap'), kt && a == e.className && (d = e.style, d.top == l.top && d.width == l.width && d.height == l.height && (h._rtl && d.right == l.right || !h._rtl && d.left == l.left))) {
                        d.display && (e.style.display = '');
                        return
                    }
                    l.textAlign = c.getAlignment();
                    p == t.CellType.Cell && h.rows.maxGroupLevel > -1 && (l.paddingLeft = l.paddingRight = '', f == h.columns.firstVisibleIndex && h.treeIndent && (gt = w ? Math.max(0, w.level) : h.rows.maxGroupLevel + 1, lt = h.treeIndent * gt + h._cellPadding, h._rtl ? l.paddingRight = lt : l.paddingLeft = lt));
                    s != !1 && (et = r.getCellData(u, f, !1), v = r.getCellData(u, f, !0), p == t.CellType.Cell && f == h.columns.firstVisibleIndex && w && w.hasChildren && !this._isEditingCell(h, u, f) ? (v || (v = w.getGroupHeader()), e.innerHTML = this._getTreeIcon(w) + ' ' + v, l.textAlign = '') : p == t.CellType.ColumnHeader && c.currentSort && h.showSort && (tt == h._getSortRowIndex() || c != k) ? (a += ' wj-sort-' + (c.currentSort == '+' ? 'asc' : 'desc'), e.innerHTML = n.escapeHtml(v) + '&nbsp;' + this._getSortIcon(c)) : p != t.CellType.RowHeader || f != h.rowHeaders.columns.length - 1 || v ? p == t.CellType.Cell && c.dataType == n.DataType.Boolean && (!w || n.isBoolean(et)) ? (b = e.firstChild, b instanceof HTMLInputElement && b.type == 'checkbox' || (e.innerHTML = '<input type="checkbox"/>', b = e.firstChild), b.checked = et == !0 ? !0 : !1, b.indeterminate = et == null, b.disabled = !h._edtHdl._allowEditing(u, f), b.disabled && (b.style.cursor = 'default'), h.editRange && h.editRange.contains(u, f) && (h._edtHdl._edt = b)) : p == t.CellType.Cell && this._isEditingCell(h, u, f) ? (yt = c.inputType, c.inputType || (yt = c.dataType == n.DataType.Number && !c.dataMap ? 'tel' : 'text'), c.dataMap || c.mask || (it = r.getCellData(u, f, !1), n.isNumber(it) && (ot = c.format, ot && it != Math.round(it) && (ot = c.format.replace(/([a-z])(\d*)(.*)/ig, '$0112$3')), v = n.Globalize.formatNumber(it, ot, !0))), e.innerHTML = '<input type="' + yt + '" class="wj-grid-editor wj-form-control">', rt = e.children[0], rt.value = v, rt.style.textAlign = c.getAlignment(), l.padding = '0px', c.mask && (ii = new n._MaskProvider(rt, c.mask)), h._edtHdl._edt = rt) : p == t.CellType.Cell && (y.isContentHtml || c.isContentHtml) ? e.innerHTML = v : n.setText(e, v) : (at = h.collectionView, vt = at ? at.currentEditItem : null, vt && y.dataItem == vt ? v = '\u270E' : n.tryCast(y, t._NewRowTemplate) && (v = '*'), n.setText(e, v)), p == t.CellType.Cell && n.input && c.dataMap && c.showDropDown !== !1 && h._edtHdl._allowEditing(u, f) && (i._ddIcon || (ni = 'position:absolute;top:0px;padding:3px 6px;opacity:.25;right:0px', i._ddIcon = n.createElement('<div style="' + ni + '" ' + i._WJA_DROPDOWN + '><span class="wj-glyph-down"></span></div>')), st = i._ddIcon.cloneNode(!0), h._rtl && (st.style.left = '0px', st.style.right = ''), e.appendChild(st)));
                    switch (p) {
                        case t.CellType.RowHeader:
                            e.removeAttribute('draggable');
                            w || pt || (h.allowDragging & t.AllowDragging.Rows) == 0 || e.setAttribute('draggable', 'true');
                            break;
                        case t.CellType.ColumnHeader:
                            e.removeAttribute('draggable');
                            (h.allowDragging & t.AllowDragging.Columns) != 0 && e.setAttribute('draggable', 'true')
                    }
                    if (e.className != a && (e.className = a), n.setCss(e, l), h.itemFormatter && h.itemFormatter(r, u, f, e), h.formatItem.hasHandlers) {
                        o = i._fmtRng;
                        o ? o.setRange(u, f, tt, ut) : o = i._fmtRng = new t.CellRange(u, f, tt, ut);
                        ti = new t.FormatItemEventArgs(r, o, e);
                        h.onFormatItem(ti)
                    }
                }, i.prototype.disposeCell = function() {}, i.prototype._isEditingCell = function(n, t, i) {
                    return n.editRange && n.editRange.contains(t, i)
                }, i.prototype._getTreeIcon = function(n) {
                    var t = 'wj-glyph-' + (n.grid._rtl ? n.isCollapsed ? 'left' : 'down-left' : n.isCollapsed ? 'right' : 'down-right');
                    return '<span ' + i._WJA_COLLAPSE + ' class="' + t + '"></span>'
                }, i.prototype._getSortIcon = function(n) {
                    return '<span class="wj-glyph-' + (n.currentSort == '+' ? 'up' : 'down') + '"></span>'
                }, i._WJA_COLLAPSE = 'wj-collapse', i._WJA_DROPDOWN = 'wj-dropdown', i
            }();
            t.CellFactory = i
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function t(n, t, i, r) {
                    n === void 0 && (n = -1);
                    t === void 0 && (t = -1);
                    i === void 0 && (i = n);
                    r === void 0 && (r = t);
                    this.setRange(n, t, i, r)
                }
                return t.prototype.setRange = function(t, i, r, u) {
                    t === void 0 && (t = -1);
                    i === void 0 && (i = -1);
                    r === void 0 && (r = t);
                    u === void 0 && (u = i);
                    this._row = n.asInt(t);
                    this._col = n.asInt(i);
                    this._row2 = n.asInt(r);
                    this._col2 = n.asInt(u)
                }, Object.defineProperty(t.prototype, "row", {
                    get: function() {
                        return this._row
                    },
                    set: function(t) {
                        this._row = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "col", {
                    get: function() {
                        return this._col
                    },
                    set: function(t) {
                        this._col = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "row2", {
                    get: function() {
                        return this._row2
                    },
                    set: function(t) {
                        this._row2 = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "col2", {
                    get: function() {
                        return this._col2
                    },
                    set: function(t) {
                        this._col2 = n.asInt(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype.clone = function() {
                    return new t(this._row, this._col, this._row2, this._col2)
                }, Object.defineProperty(t.prototype, "rowSpan", {
                    get: function() {
                        return Math.abs(this._row2 - this._row) + 1
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "columnSpan", {
                    get: function() {
                        return Math.abs(this._col2 - this._col) + 1
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "topRow", {
                    get: function() {
                        return Math.min(this._row, this._row2)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "bottomRow", {
                    get: function() {
                        return Math.max(this._row, this._row2)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "leftCol", {
                    get: function() {
                        return Math.min(this._col, this._col2)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "rightCol", {
                    get: function() {
                        return Math.max(this._col, this._col2)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "isValid", {
                    get: function() {
                        return this._row > -1 && this._col > -1 && this._row2 > -1 && this._col2 > -1
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "isSingleCell", {
                    get: function() {
                        return this._row == this._row2 && this._col == this._col2
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype.contains = function(i, r) {
                    var u = n.tryCast(i, t);
                    if (u) return u.topRow >= this.topRow && u.bottomRow <= this.bottomRow && u.leftCol >= this.leftCol && u.rightCol <= this.rightCol;
                    if (n.isInt(i) && n.isInt(r)) return i >= this.topRow && i <= this.bottomRow && r >= this.leftCol && r <= this.rightCol;
                    throw 'contains expects a CellRange or row/column indices.';
                }, t.prototype.containsRow = function(t) {
                    return n.asInt(t) >= this.topRow && t <= this.bottomRow
                }, t.prototype.containsColumn = function(t) {
                    return n.asInt(t) >= this.leftCol && t <= this.rightCol
                }, t.prototype.intersects = function(n) {
                    return this.intersectsRow(n) && this.intersectsColumn(n)
                }, t.prototype.intersectsRow = function(n) {
                    return n && !(this.bottomRow < n.topRow || this.topRow > n.bottomRow)
                }, t.prototype.intersectsColumn = function(n) {
                    return n && !(this.rightCol < n.leftCol || this.leftCol > n.rightCol)
                }, t.prototype.getRenderSize = function(t) {
                    var u = new n.Size(0, 0),
                        i, r;
                    if (this.isValid) {
                        for (i = this.topRow; i <= this.bottomRow; i++) u.height += t.rows[i].renderSize;
                        for (r = this.leftCol; r <= this.rightCol; r++) u.width += t.columns[r].renderSize
                    }
                    return u
                }, t.prototype.equals = function(n) {
                    return n instanceof t && this._row == n._row && this._col == n._col && this._row2 == n._row2 && this._col2 == n._col2
                }, t
            }();
            t.CellRange = i
        })(t = n.grid || (n.grid = {}))
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
            var i, u, r, e, o, f, s, h;
            (function(n) {
                n[n.Visible = 1] = "Visible";
                n[n.AllowResizing = 2] = "AllowResizing";
                n[n.AllowDragging = 4] = "AllowDragging";
                n[n.AllowMerging = 8] = "AllowMerging";
                n[n.AllowSorting = 16] = "AllowSorting";
                n[n.AutoGenerated = 32] = "AutoGenerated";
                n[n.Collapsed = 64] = "Collapsed";
                n[n.ParentCollapsed = 128] = "ParentCollapsed";
                n[n.Selected = 256] = "Selected";
                n[n.ReadOnly = 512] = "ReadOnly";
                n[n.HtmlContent = 1024] = "HtmlContent";
                n[n.WordWrap = 2048] = "WordWrap";
                n[n.RowDefault = 3] = "RowDefault";
                n[n.ColumnDefault = 23] = "ColumnDefault"
            })(t.RowColFlags || (t.RowColFlags = {}));
            i = t.RowColFlags;
            u = function() {
                function r() {
                    this._list = null;
                    this._pos = 0;
                    this._idx = -1
                }
                return Object.defineProperty(r.prototype, "visible", {
                    get: function() {
                        return this._getFlag(i.Visible)
                    },
                    set: function(n) {
                        this._setFlag(i.Visible, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "isVisible", {
                    get: function() {
                        return this._getFlag(i.Visible) ? this._getFlag(i.ParentCollapsed) && !(this instanceof t._NewRowTemplate) ? !1 : !0 : !1
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "pos", {
                    get: function() {
                        return this._list && this._list._update(), this._pos
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "index", {
                    get: function() {
                        return this._list && this._list._update(), this._idx
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "size", {
                    get: function() {
                        return this._sz
                    },
                    set: function(t) {
                        t != this._sz && (this._sz = n.asNumber(t, !0), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "renderSize", {
                    get: function() {
                        if (!this.isVisible) return 0;
                        var n = this._sz,
                            t = this._list;
                        return (n == null || n < 0) && t != null ? Math.round(t.defaultSize) : (t != null && (t.minSize != null && n < t.minSize && (n = t.minSize), t.maxSize != null && n > t.maxSize && (n = t.maxSize)), this._szMin != null && n < this._szMin && (n = this._szMin), this._szMax != null && n > this._szMax && (n = this._szMax), Math.round(n))
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "allowResizing", {
                    get: function() {
                        return this._getFlag(i.AllowResizing)
                    },
                    set: function(n) {
                        this._setFlag(i.AllowResizing, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "allowDragging", {
                    get: function() {
                        return this._getFlag(i.AllowDragging)
                    },
                    set: function(n) {
                        this._setFlag(i.AllowDragging, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "allowMerging", {
                    get: function() {
                        return this._getFlag(i.AllowMerging)
                    },
                    set: function(n) {
                        this._setFlag(i.AllowMerging, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "isSelected", {
                    get: function() {
                        return this._getFlag(i.Selected)
                    },
                    set: function(n) {
                        this._setFlag(i.Selected, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "isReadOnly", {
                    get: function() {
                        return this._getFlag(i.ReadOnly)
                    },
                    set: function(n) {
                        this._setFlag(i.ReadOnly, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "isContentHtml", {
                    get: function() {
                        return this._getFlag(i.HtmlContent)
                    },
                    set: function(n) {
                        this.isContentHtml != n && (this._setFlag(i.HtmlContent, n), this.grid && this.grid.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "wordWrap", {
                    get: function() {
                        return this._getFlag(i.WordWrap)
                    },
                    set: function(n) {
                        this._setFlag(i.WordWrap, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "cssClass", {
                    get: function() {
                        return this._cssClass
                    },
                    set: function(t) {
                        t != this._cssClass && (this._cssClass = n.asString(t), this.grid && this.grid.invalidate(!1))
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "grid", {
                    get: function() {
                        return this._list ? this._list._g : null
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "collectionView", {
                    get: function() {
                        return this.grid ? this.grid.collectionView : null
                    },
                    enumerable: !0,
                    configurable: !0
                }), r.prototype.onPropertyChanged = function() {
                    this._list && (this._list._dirty = !0, this.grid.invalidate())
                }, r.prototype._getFlag = function(n) {
                    return (this._f & n) != 0
                }, r.prototype._setFlag = function(n, t, i) {
                    return t != this._getFlag(n) ? (this._f = t ? this._f | n : this._f & ~n, i || this.onPropertyChanged(), !0) : !1
                }, r
            }();
            t.RowCol = u;
            r = function(r) {
                function u(t) {
                    r.call(this);
                    this._f = i.ColumnDefault;
                    this._hash = u._ctr.toString(36);
                    u._ctr++;
                    t && n.copy(this, t)
                }
                return __extends(u, r), Object.defineProperty(u.prototype, "name", {
                    get: function() {
                        return this._name
                    },
                    set: function(n) {
                        this._name = n
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "dataType", {
                    get: function() {
                        return this._type
                    },
                    set: function(t) {
                        this._type != t && (this._type = n.asEnum(t, n.DataType), this.grid && this.grid.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "required", {
                    get: function() {
                        return this._required
                    },
                    set: function(t) {
                        this._required = n.asBoolean(t, !0)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "showDropDown", {
                    get: function() {
                        return this._showDropDown
                    },
                    set: function(t) {
                        t != this._showDropDown && (this._showDropDown = n.asBoolean(t, !0), this.grid && this.grid.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "dropDownCssClass", {
                    get: function() {
                        return this._ddCssClass
                    },
                    set: function(t) {
                        this._ddCssClass = n.asString(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "inputType", {
                    get: function() {
                        return this._inpType
                    },
                    set: function(t) {
                        this._inpType = n.asString(t, !0)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "mask", {
                    get: function() {
                        return this._mask
                    },
                    set: function(t) {
                        this._mask = n.asString(t, !0)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "binding", {
                    get: function() {
                        return this._binding ? this._binding.path : null
                    },
                    set: function(t) {
                        var r, i, u;
                        t != this.binding && (r = n.asString(t), this._binding = r ? new n.Binding(r) : null, !this._type && this.grid && this._binding && (i = this.grid.collectionView, i && i.sourceCollection && i.sourceCollection.length && (u = i.sourceCollection[0], this._type = n.getType(this._binding.getValue(u)))), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "sortMemberPath", {
                    get: function() {
                        return this._bindingSort ? this._bindingSort.path : null
                    },
                    set: function(t) {
                        if (t != this.sortMemberPath) {
                            var i = n.asString(t);
                            this._bindingSort = i ? new n.Binding(i) : null;
                            this.onPropertyChanged()
                        }
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "width", {
                    get: function() {
                        return this._szStar != null ? this._szStar : this.size
                    },
                    set: function(t) {
                        u._parseStarSize(t) != null ? (this._szStar = t, this.onPropertyChanged()) : (this._szStar = null, this.size = n.asNumber(t, !0))
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "minWidth", {
                    get: function() {
                        return this._szMin
                    },
                    set: function(t) {
                        t != this._szMin && (this._szMin = n.asNumber(t, !0, !0), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "maxWidth", {
                    get: function() {
                        return this._szMax
                    },
                    set: function(t) {
                        t != this._szMax && (this._szMax = n.asNumber(t, !0, !0), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "renderWidth", {
                    get: function() {
                        return this.renderSize
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "align", {
                    get: function() {
                        return this._align
                    },
                    set: function(n) {
                        this._align != n && (this._align = n, this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), u.prototype.getAlignment = function() {
                    var t = this._align;
                    if (t == null && (t = '', !this._map)) switch (this._type) {
                        case n.DataType.Boolean:
                            t = 'center';
                            break;
                        case n.DataType.Number:
                            t = 'right'
                    }
                    return t
                }, Object.defineProperty(u.prototype, "header", {
                    get: function() {
                        return this._hdr ? this._hdr : this.binding
                    },
                    set: function(n) {
                        this._hdr != n && (this._hdr = n, this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "dataMap", {
                    get: function() {
                        return this._map
                    },
                    set: function(i) {
                        this._map != i && (this._map && this._map.mapChanged.removeHandler(this.onPropertyChanged, this), n.isArray(i) && (i = new t.DataMap(i, null, null)), this._map = n.asType(i, t.DataMap, !0), this._map && this._map.mapChanged.addHandler(this.onPropertyChanged, this), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "format", {
                    get: function() {
                        return this._fmt
                    },
                    set: function(n) {
                        this._fmt != n && (this._fmt = n, this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "allowSorting", {
                    get: function() {
                        return this._getFlag(i.AllowSorting)
                    },
                    set: function(n) {
                        this._setFlag(i.AllowSorting, n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "currentSort", {
                    get: function() {
                        var t, n;
                        if (this.grid && this.grid.collectionView && this.grid.collectionView.canSort)
                            for (t = this.grid.collectionView.sortDescriptions, n = 0; n < t.length; n++)
                                if (t[n].property == this._getBindingSort()) return t[n].ascending ? '+' : '-';
                        return null
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "aggregate", {
                    get: function() {
                        return this._agg != null ? this._agg : n.Aggregate.None
                    },
                    set: function(t) {
                        t != this._agg && (this._agg = n.asEnum(t, n.Aggregate), this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), u.prototype._getBindingSort = function() {
                    return this.sortMemberPath ? this.sortMemberPath : this.binding ? this.binding : null
                }, u._parseStarSize = function(t) {
                    if (n.isString(t) && t.length > 0 && t[t.length - 1] == '*') {
                        var i = t.length == 1 ? 1 : t.substr(0, t.length - 1) * 1;
                        if (i > 0 && !isNaN(i)) return i
                    }
                    return null
                }, u._ctr = 0, u
            }(u);
            t.Column = r;
            e = function(n) {
                function t(t) {
                    n.call(this);
                    this._f = i.ColumnDefault;
                    this._data = t
                }
                return __extends(t, n), Object.defineProperty(t.prototype, "dataItem", {
                    get: function() {
                        return this._data
                    },
                    set: function(n) {
                        this._data = n
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "height", {
                    get: function() {
                        return this.size
                    },
                    set: function(n) {
                        this.size = n
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "renderHeight", {
                    get: function() {
                        return this.renderSize
                    },
                    enumerable: !0,
                    configurable: !0
                }), t
            }(u);
            t.Row = e;
            o = function(r) {
                function u() {
                    r.call(this);
                    this._level = -1;
                    this.isReadOnly = !0
                }
                return __extends(u, r), Object.defineProperty(u.prototype, "level", {
                    get: function() {
                        return this._level
                    },
                    set: function(t) {
                        n.asInt(t);
                        t != this._level && (this._level = t, this.onPropertyChanged())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "hasChildren", {
                    get: function() {
                        if (this.grid != null && this._list != null) {
                            this._list._update();
                            var i = this.index < this._list.length - 1 ? this._list[this.index + 1] : null,
                                r = n.tryCast(i, u),
                                f = n.tryCast(i, t._NewRowTemplate);
                            return i && f == null && (r == null || r.level > this.level)
                        }
                        return !0
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "isCollapsed", {
                    get: function() {
                        return this._getFlag(i.Collapsed)
                    },
                    set: function(t) {
                        n.asBoolean(t);
                        t != this.isCollapsed && this._list != null && this._setCollapsed(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), u.prototype.getGroupHeader = function() {
                    var u = this.grid,
                        o = u.groupHeaderFormat ? u.groupHeaderFormat : n.culture.FlexGrid.groupHeaderFormat,
                        r = n.tryCast(this.dataItem, n.collections.CollectionViewGroup);
                    if (r && o) {
                        var f = r.groupDescription.propertyName,
                            i = r.name,
                            t = u.columns.getColumn(f),
                            e = this.isContentHtml;
                        return t && (e = e || t.isContentHtml, t.header && (f = t.header), t.dataMap ? i = t.dataMap.getDisplayValue(i) : t.format && (i = n.Globalize.format(i, t.format))), n.format(o, {
                            name: n.escapeHtml(f),
                            value: e ? i : n.escapeHtml(i),
                            level: r.level,
                            count: r.items.length
                        })
                    }
                    return ''
                }, u.prototype._setCollapsed = function(r) {
                    var c = this,
                        f = this.grid,
                        o = f.rows,
                        h = this.getCellRange(),
                        s = new t.CellRangeEventArgs(f.cells, new t.CellRange(this.index, -1)),
                        e;
                    f.onGroupCollapsedChanging(s);
                    if (!s.cancel) {
                        f.deferUpdate(function() {
                            c._setFlag(i.Collapsed, r);
                            for (var t = h.topRow + 1; t <= h.bottomRow && t > -1 && t < o.length; t++) o[t]._setFlag(i.ParentCollapsed, r), e = n.tryCast(o[t], u), e != null && e.isCollapsed && (t = e.getCellRange().bottomRow)
                        });
                        f.onGroupCollapsedChanged(s)
                    }
                }, u.prototype.getCellRange = function() {
                    for (var f, e = this._list, o = this.index, r = e.length - 1, i = o + 1; i <= r; i++)
                        if (f = n.tryCast(e[i], u), f != null && f.level <= this.level) {
                            r = i - 1;
                            break
                        }
                    return new t.CellRange(o, 0, r, this.grid.columns.length - 1)
                }, u
            }(e);
            t.GroupRow = o;
            f = function(i) {
                function r(r, u) {
                    i.call(this);
                    this._frozen = 0;
                    this._szDef = 28;
                    this._szTot = 0;
                    this._dirty = !1;
                    this._g = n.asType(r, t.FlexGrid);
                    this._szDef = n.asNumber(u, !1, !0)
                }
                return __extends(r, i), Object.defineProperty(r.prototype, "defaultSize", {
                    get: function() {
                        return this._szDef
                    },
                    set: function(t) {
                        this._szDef != t && (this._szDef = n.asNumber(t, !1, !0), this._dirty = !0, this._g.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "frozen", {
                    get: function() {
                        return this._frozen
                    },
                    set: function(t) {
                        t != this._frozen && (this._frozen = n.asNumber(t, !1, !0), this._dirty = !0, this._g.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), r.prototype.isFrozen = function(n) {
                    return n < this.frozen
                }, Object.defineProperty(r.prototype, "minSize", {
                    get: function() {
                        return this._szMin
                    },
                    set: function(t) {
                        t != this._szMin && (this._szMin = n.asNumber(t, !0, !0), this._dirty = !0, this._g.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "maxSize", {
                    get: function() {
                        return this._szMax
                    },
                    set: function(t) {
                        t != this._szMax && (this._szMax = n.asNumber(t, !0, !0), this._dirty = !0, this._g.invalidate())
                    },
                    enumerable: !0,
                    configurable: !0
                }), r.prototype.getTotalSize = function() {
                    return this._update(), this._szTot
                }, r.prototype.getItemAt = function(n) {
                    if (this._update(), n <= 0 && this.length > 0) return 0;
                    for (var u = 0, i = this.length - 1, t, r; u <= i;)
                        if (t = u + i >>> 1, r = this[t], r._pos > n) i = t - 1;
                        else if (r._pos + r.renderSize < n) u = t + 1;
                    else return t;
                    return i
                }, r.prototype.getNextCell = function(n, i, r) {
                    var u, f;
                    switch (i) {
                        case t.SelMove.Next:
                            for (u = n + 1; u < this.length; u++)
                                if (this[u].renderSize > 0) return u;
                            break;
                        case t.SelMove.Prev:
                            for (u = n - 1; u >= 0; u--)
                                if (this[u].renderSize > 0) return u;
                            break;
                        case t.SelMove.End:
                            for (u = this.length - 1; u >= 0; u--)
                                if (this[u].renderSize > 0) return u;
                            break;
                        case t.SelMove.Home:
                            for (u = 0; u < this.length; u++)
                                if (this[u].renderSize > 0) return u;
                            break;
                        case t.SelMove.NextPage:
                            return f = this.getItemAt(this[n].pos + r), f < 0 ? this.getNextCell(n, t.SelMove.End, r) : f;
                        case t.SelMove.PrevPage:
                            return f = this.getItemAt(this[n].pos - r), f < 0 ? this.getNextCell(n, t.SelMove.Home, r) : f
                    }
                    return n
                }, r.prototype.canMoveElement = function(n, i) {
                    var u, f, r;
                    if (i == n || n < 0 || n >= this.length || i >= this.length) return !1;
                    for (i < 0 && (i = this.length - 1), u = Math.min(n, i), f = Math.max(n, i), r = u; r <= f; r++)
                        if (!this[r].allowDragging) return !1;
                    return this[i] instanceof t._NewRowTemplate ? !1 : !0
                }, r.prototype.moveElement = function(n, t) {
                    if (this.canMoveElement(n, t)) {
                        var i = this[n];
                        this.removeAt(n);
                        t < 0 && (t = this.length);
                        this.insert(t, i)
                    }
                }, r.prototype.onCollectionChanged = function(t) {
                    t === void 0 && (t = n.collections.NotifyCollectionChangedEventArgs.reset);
                    this._dirty = !0;
                    this._g.invalidate();
                    i.prototype.onCollectionChanged.call(this, t)
                }, r.prototype.push = function(n) {
                    return n._list = this, i.prototype.push.call(this, n)
                }, r.prototype.splice = function(n, t, r) {
                    return r && (r._list = this), i.prototype.splice.call(this, n, t, r)
                }, r.prototype.beginUpdate = function() {
                    this._update();
                    i.prototype.beginUpdate.call(this)
                }, r.prototype._update = function() {
                    var i, n, t;
                    if (this._dirty && !this.isUpdating) {
                        for (this._dirty = !1, i = 0, t = 0; t < this.length; t++) n = this[t], n._idx = t, n._list = this, n._pos = i, i += n.renderSize;
                        return this._szTot = i, !0
                    }
                    return !1
                }, r
            }(n.collections.ObservableArray);
            t.RowColCollection = f;
            s = function(n) {
                function t() {
                    n.apply(this, arguments);
                    this._firstVisible = -1
                }
                return __extends(t, n), t.prototype.getColumn = function(n) {
                    var t = this.indexOf(n);
                    return t > -1 ? this[t] : null
                }, t.prototype.indexOf = function(t) {
                    var i;
                    if (t instanceof r) return n.prototype.indexOf.call(this, t);
                    for (i = 0; i < this.length; i++)
                        if (this[i].name == t) return i;
                    for (i = 0; i < this.length; i++)
                        if (this[i].binding == t) return i;
                    return -1
                }, Object.defineProperty(t.prototype, "firstVisibleIndex", {
                    get: function() {
                        return this._update(), this._firstVisible
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype._update = function() {
                    if (n.prototype._update.call(this)) {
                        this._firstVisible = -1;
                        for (var t = 0; t < this.length; t++)
                            if (this[t].visible) {
                                this._firstVisible = t;
                                break
                            }
                        return !0
                    }
                    return !1
                }, t.prototype._updateStarSizes = function(n) {
                    for (var e = 0, t, u, f, i = 0; i < this.length; i++) t = this[i], t.isVisible && (t._szStar ? (e += r._parseStarSize(t._szStar), u = t) : n -= t.renderWidth);
                    if (u != null) {
                        for (f = n, i = 0; i < this.length; i++) t = this[i], t.isVisible && t._szStar && (t == u ? t._sz = f : (t._sz = Math.max(0, Math.round(r._parseStarSize(t._szStar) / e * n)), f -= t.renderWidth));
                        return this._dirty = !0, this._update(), !0
                    }
                    return !1
                }, t
            }(f);
            t.ColumnCollection = s;
            h = function(t) {
                function i() {
                    t.apply(this, arguments);
                    this._maxLevel = -1
                }
                return __extends(i, t), Object.defineProperty(i.prototype, "maxGroupLevel", {
                    get: function() {
                        return this._update(), this._maxLevel
                    },
                    enumerable: !0,
                    configurable: !0
                }), i.prototype._update = function() {
                    var i, r;
                    if (t.prototype._update.call(this)) {
                        for (this._maxLevel = -1, i = 0; i < this.length; i++) r = n.tryCast(this[i], o), r && r.level > this._maxLevel && (this._maxLevel = r.level);
                        return !0
                    }
                    return !1
                }, i
            }(f);
            t.RowCollection = h
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(i) {
            'use strict';
            var r = function() {
                function r(t, u) {
                    var o, l, a;
                    if (this._row = -1, this._col = -1, this._edge = 0, t instanceof i.FlexGrid) this._g = t;
                    else if (t instanceof i.GridPanel) this._p = t, t = this._g = this._p.grid;
                    else throw 'First parameter should be a FlexGrid or GridPanel.';
                    u = n.mouseToPage(u);
                    this._pt = u.clone();
                    var v = t.controlRect,
                        k = t.scrollPosition,
                        y = t.clientSize,
                        d = t.topLeftCells,
                        g = t._eTL,
                        p = t.headersVisibility,
                        e = p & i.HeadersVisibility.Row ? d.columns.getTotalSize() : 0,
                        s = p & i.HeadersVisibility.Column ? d.rows.getTotalSize() : 0,
                        h = p & i.HeadersVisibility.Column ? s + g.offsetTop : 0;
                    if (u.x -= v.left, u.y -= v.top, this._g._rtl && (u.x = v.width - u.x), !this._p && u.x >= 0 && u.y >= 0 && y && u.x <= y.width + e && u.y <= y.height + h && (this._p = u.x <= e && u.y <= h ? t.topLeftCells : u.x <= e ? t.rowHeaders : u.y <= h ? t.columnHeaders : t.cells), this._p != null) {
                        var w = this._p.rows,
                            b = this._p.columns,
                            f = this._p.cellType,
                            nt = f == i.CellType.ColumnHeader || f == i.CellType.TopLeft ? s : w.getTotalSize(),
                            tt = f == i.CellType.RowHeader || f == i.CellType.TopLeft ? e : b.getTotalSize(),
                            c = this._p._getFrozenPos();
                        if ((f == i.CellType.Cell || f == i.CellType.RowHeader) && (u.y -= s, (u.y > c.y || c.y <= 0) && (u.y -= k.y, u.y += this._p._getOffsetY())), (f == i.CellType.Cell || f == i.CellType.ColumnHeader) && (u.x -= e, (u.x > c.x || c.x <= 0) && (u.x -= k.x)), (f == i.CellType.ColumnHeader || f == i.CellType.TopLeft) && (u.y -= h - s), this._row = u.y > nt ? -1 : w.getItemAt(u.y), this._col = u.x > tt ? -1 : b.getItemAt(u.x), this._row < 0 || this._col < 0) {
                            this._p = null;
                            return
                        }
                        this._edge = 0;
                        o = r._EDGESIZE;
                        this._col > -1 && (l = b[this._col], u.x - l.pos <= o && (this._edge |= 1), l.pos + l.renderSize - u.x <= o && (this._edge |= 4));
                        this._row > -1 && (a = w[this._row], u.y - a.pos <= o && (this._edge |= 2), a.pos + a.renderSize - u.y <= o && (this._edge |= 8))
                    }
                }
                return Object.defineProperty(r.prototype, "point", {
                    get: function() {
                        return this._pt
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "cellType", {
                    get: function() {
                        return this._p ? this._p.cellType : t.CellType.None
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "panel", {
                    get: function() {
                        return this._p
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "row", {
                    get: function() {
                        return this._row
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "col", {
                    get: function() {
                        return this._col
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "range", {
                    get: function() {
                        return new i.CellRange(this._row, this._col)
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "edgeLeft", {
                    get: function() {
                        return (this._edge & 1) != 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "edgeTop", {
                    get: function() {
                        return (this._edge & 2) != 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "edgeRight", {
                    get: function() {
                        return (this._edge & 4) != 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(r.prototype, "edgeBottom", {
                    get: function() {
                        return (this._edge & 8) != 0
                    },
                    enumerable: !0,
                    configurable: !0
                }), r._EDGESIZE = 5, r
            }();
            i.HitTestInfo = r
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            (function(n) {
                n[n.None = 0] = "None";
                n[n.Cells = 1] = "Cells";
                n[n.ColumnHeaders = 2] = "ColumnHeaders";
                n[n.RowHeaders = 4] = "RowHeaders";
                n[n.AllHeaders = 6] = "AllHeaders";
                n[n.All = 7] = "All"
            })(t.AllowMerging || (t.AllowMerging = {}));
            var i = t.AllowMerging,
                r = function() {
                    function r(n) {
                        this._g = n
                    }
                    return r.prototype.getMergedRange = function(r, u, f, e) {
                        var c, g, b, v, y, nt, k, p, w;
                        e === void 0 && (e = !0);
                        var o, l, h = r.cellType,
                            s = r.columns,
                            a = r.rows,
                            d = a[u],
                            tt = s[f];
                        if (d instanceof t._NewRowTemplate) return null;
                        if (d instanceof t.GroupRow && d.dataItem instanceof n.collections.CollectionViewGroup) {
                            if (o = new t.CellRange(u, f), tt.aggregate == n.Aggregate.None) {
                                while (o.col > 0 && s[o.col - 1].aggregate == n.Aggregate.None && o.col != s.frozen) o.col--;
                                while (o.col2 < s.length - 1 && s[o.col2 + 1].aggregate == n.Aggregate.None && o.col2 + 1 != s.frozen) o.col2++
                            }
                            while (o.col < f && !s[o.col].visible) o.col++;
                            return o.isSingleCell ? null : o
                        }
                        c = !1;
                        switch (this._g.allowMerging) {
                            case i.None:
                                c = !0;
                                break;
                            case i.Cells:
                                c = h != t.CellType.Cell;
                                break;
                            case i.ColumnHeaders:
                                c = h != t.CellType.ColumnHeader && h != t.CellType.TopLeft;
                                break;
                            case i.RowHeaders:
                                c = h != t.CellType.RowHeader && h != t.CellType.TopLeft;
                                break;
                            case i.AllHeaders:
                                c = h == t.CellType.Cell
                        }
                        if (c) return null;
                        if (s[f].allowMerging) {
                            for (o = new t.CellRange(u, f), g = 0, b = a.length - 1, u >= a.frozen ? e && (h == t.CellType.Cell || h == t.CellType.RowHeader) && (l = r._getViewRange(!0), g = l.topRow, b = l.bottomRow) : b = a.frozen - 1, v = u - 1; v >= g && this._mergeCell(r, v, f, u, f); v--) o.row = v;
                            for (y = u + 1; y <= b && this._mergeCell(r, u, f, y, f); y++) o.row2 = y;
                            while (o.row < u && !a[o.row].visible) o.row++;
                            if (!o.isSingleCell) return o
                        }
                        if (a[u].allowMerging) {
                            for (o = new t.CellRange(u, f), nt = 0, k = s.length - 1, f >= s.frozen ? e && (h == t.CellType.Cell || h == t.CellType.ColumnHeader) && (l = r._getViewRange(!0), nt = l.leftCol, k = l.rightCol) : k = s.frozen - 1, p = f - 1; p >= nt && this._mergeCell(r, u, p, u, f); p--) o.col = p;
                            for (w = f + 1; w <= k && this._mergeCell(r, u, f, u, w); w++) o.col2 = w;
                            while (o.col < f && !s[o.col].visible) o.col++;
                            if (!o.isSingleCell) return o
                        }
                        return null
                    }, r.prototype._mergeCell = function(n, i, r, u, f) {
                        var e = n.rows[i],
                            o = n.rows[u];
                        return e instanceof t.GroupRow || e instanceof t._NewRowTemplate || o instanceof t.GroupRow || o instanceof t._NewRowTemplate ? !1 : i != u && n.rows.isFrozen(i) != n.rows.isFrozen(u) ? !1 : r != f && n.columns.isFrozen(r) != n.columns.isFrozen(f) ? !1 : i != u && (r > 0 && (e.allowMerging && this._mergeCell(n, i, r - 1, i, r) || o.allowMerging && this._mergeCell(n, u, r - 1, u, r)) || f < n.columns.length - 1 && (e.allowMerging && this._mergeCell(n, i, f, i, f + 1) || o.allowMerging && this._mergeCell(n, u, f, u, f + 1))) ? !1 : n.getCellData(i, r, !0) != n.getCellData(u, f, !0) ? !1 : !0
                    }, r
                }();
            t.MergeManager = r
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function t(t, i, r) {
                    var f, u;
                    if (this.mapChanged = new n.Event, n.isArray(t) && !i && !r) {
                        for (f = [], u = 0; u < t.length; u++) f.push({
                            value: t[u]
                        });
                        t = f;
                        i = r = 'value'
                    }
                    this._cv = n.asCollectionView(t);
                    this._keyPath = n.asString(i, !1);
                    this._displayPath = n.asString(r, !1);
                    this._cv.collectionChanged.addHandler(this.onMapChanged, this)
                }
                return Object.defineProperty(t.prototype, "collectionView", {
                    get: function() {
                        return this._cv
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "selectedValuePath", {
                    get: function() {
                        return this._keyPath
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(t.prototype, "displayMemberPath", {
                    get: function() {
                        return this._displayPath
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype.getKeyValue = function(n) {
                    var t = this._indexOf(n, this._displayPath, !1);
                    return t > -1 ? this._cv.sourceCollection[t][this._keyPath] : null
                }, t.prototype.getDisplayValue = function(n) {
                    var t = this._indexOf(n, this._keyPath, !0);
                    return t > -1 ? this._cv.sourceCollection[t][this._displayPath] : n
                }, t.prototype.getDisplayValues = function() {
                    var i = [],
                        t, n;
                    if (this._cv && this._displayPath)
                        for (t = this._cv.items, n = 0; n < t.length; n++) i.push(t[n][this._displayPath]);
                    return i
                }, t.prototype.getKeyValues = function() {
                    var i = [],
                        t, n;
                    if (this._cv && this._keyPath)
                        for (t = this._cv.items, n = 0; n < t.length; n++) i.push(t[n][this._keyPath]);
                    return i
                }, Object.defineProperty(t.prototype, "isEditable", {
                    get: function() {
                        return this._editable
                    },
                    set: function(t) {
                        this._editable = n.asBoolean(t)
                    },
                    enumerable: !0,
                    configurable: !0
                }), t.prototype.onMapChanged = function() {
                    this.mapChanged.raise(this)
                }, t.prototype._indexOf = function(n, t, i) {
                    var u, r;
                    if (this._cv && t) {
                        var f = n != null ? n.toString() : '',
                            e = i ? f : f.toLowerCase(),
                            o = this._cv.sourceCollection;
                        for (u = 0; u < o.length; u++)
                            if ((r = o[u][t], r == n) || !i && r.length == e.length && r.toLowerCase() == e || r != null && r.toString() == f) return u
                    }
                    return -1
                }, t
            }();
            t.DataMap = i
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var r, f, i, u;
            (function(n) {
                n[n.None = 0] = "None";
                n[n.Cell = 1] = "Cell";
                n[n.CellRange = 2] = "CellRange";
                n[n.Row = 3] = "Row";
                n[n.RowRange = 4] = "RowRange";
                n[n.ListBox = 5] = "ListBox"
            })(t.SelectionMode || (t.SelectionMode = {}));
            r = t.SelectionMode,
                function(n) {
                    n[n.None = 0] = "None";
                    n[n.Selected = 1] = "Selected";
                    n[n.Cursor = 2] = "Cursor"
                }(t.SelectedState || (t.SelectedState = {}));
            f = t.SelectedState,
                function(n) {
                    n[n.None = 0] = "None";
                    n[n.Next = 1] = "Next";
                    n[n.Prev = 2] = "Prev";
                    n[n.NextPage = 3] = "NextPage";
                    n[n.PrevPage = 4] = "PrevPage";
                    n[n.Home = 5] = "Home";
                    n[n.End = 6] = "End";
                    n[n.NextCell = 7] = "NextCell";
                    n[n.PrevCell = 8] = "PrevCell"
                }(t.SelMove || (t.SelMove = {}));
            i = t.SelMove;
            u = function() {
                function u(n) {
                    this._sel = new t.CellRange(0, 0);
                    this._mode = r.CellRange;
                    this._g = n
                }
                return Object.defineProperty(u.prototype, "selectionMode", {
                    get: function() {
                        return this._mode
                    },
                    set: function(n) {
                        var u, i;
                        if (n != this._mode) {
                            if (n == r.ListBox || this._mode == r.ListBox)
                                for (u = this._g.rows, i = 0; i < u.length; i++) u[i]._setFlag(t.RowColFlags.Selected, n == r.ListBox ? this._sel.containsRow(i) : !1, !1);
                            switch (n) {
                                case r.None:
                                    this._sel.setRange(-1, -1);
                                    break;
                                case r.Cell:
                                    this._sel.row2 = this._sel.row;
                                    this._sel.col2 = this._sel.col;
                                    break;
                                case r.Row:
                                    this._sel.row2 = this._sel.row
                            }
                            this._mode = n;
                            this._g.invalidate()
                        }
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(u.prototype, "selection", {
                    get: function() {
                        return this._sel
                    },
                    set: function(n) {
                        this.select(n)
                    },
                    enumerable: !0,
                    configurable: !0
                }), u.prototype.select = function(i, u) {
                    var h, c, o, l;
                    u === void 0 && (u = !0);
                    n.isNumber(i) && n.isNumber(u) && (i = new t.CellRange(i, u), u = !0);
                    i = n.asType(i, t.CellRange);
                    var f = this._g,
                        a = this._sel,
                        e = i,
                        s = !1;
                    switch (f.selectionMode) {
                        case r.Cell:
                            i.row2 = i.row;
                            i.col2 = i.col;
                            break;
                        case r.Row:
                            i.row2 = i.row;
                            break;
                        case r.ListBox:
                            s = !0
                    }
                    if (h = e.equals(a), s && e.row > -1 && !f.rows[e.row].isSelected && (h = !1), h) {
                        u && f.scrollIntoView(e.row, e.col);
                        return
                    }
                    if (c = new t.CellRangeEventArgs(f.cells, e), f.onSelectionChanging(c)) {
                        if (s) {
                            for (o = 0; o < f.rows.length; o++) f.rows[o]._setFlag(t.RowColFlags.Selected, e.containsRow(o), !1);
                            f.refreshCells(!1, !0, !0)
                        }
                        e.row = Math.min(e.row, f.rows.length - 1);
                        e.row2 = Math.min(e.row2, f.rows.length - 1);
                        this._sel = e;
                        f.refreshCells(!1, !0, !0);
                        u && f.scrollIntoView(e.row, e.col);
                        f.collectionView && (l = f._getCvIndex(e.row), f.collectionView.moveCurrentToPosition(l));
                        f.onSelectionChanged(c)
                    }
                }, u.prototype.moveSelection = function(n, r, u) {
                    var e, f, h = this._g,
                        l = h.rows,
                        c = h.columns,
                        o = this._getReferenceCell(n, r, u),
                        s = Math.max(0, h.clientSize.height - h.columnHeaders.height),
                        a;
                    r == i.NextCell ? (f = c.getNextCell(o.col, i.Next, s), e = o.row, f == o.col && (e = l.getNextCell(e, i.Next, s), e > o.row && (f = c.getNextCell(0, i.Next, s), f = c.getNextCell(f, i.Prev, s))), h.select(e, f)) : r == i.PrevCell ? (f = c.getNextCell(o.col, i.Prev, s), e = o.row, f == o.col && (e = l.getNextCell(e, i.Prev, s), e < o.row && (f = c.getNextCell(c.length - 1, i.Prev, s), f = c.getNextCell(f, i.Next, s))), h.select(e, f)) : (e = l.getNextCell(o.row, n, s), f = c.getNextCell(o.col, r, s), u ? (a = h._selHdl._sel, h.select(new t.CellRange(e, f, a.row2, a.col2))) : h.select(e, f))
                }, u.prototype._getReferenceCell = function(n, t) {
                    var f = this._g,
                        u = f._selHdl._sel,
                        r = f.getMergedRange(f.cells, u.row, u.col);
                    if (!r || r.isSingleCell) return u;
                    r = r.clone();
                    switch (n) {
                        case i.Next:
                        case i.NextCell:
                            r.row = r.bottomRow;
                            break;
                        case i.None:
                            r.row = u.row
                    }
                    switch (t) {
                        case i.Next:
                        case i.NextCell:
                            r.col = r.rightCol;
                            break;
                        case i.None:
                            r.col = u.col
                    }
                    return r
                }, u.prototype._adjustSelection = function(n) {
                    switch (this._mode) {
                        case r.Cell:
                            return new t.CellRange(n.row, n.col, n.row, n.col);
                        case r.Row:
                            return new t.CellRange(n.row, 0, n.row, this._g.columns.length - 1);
                        case r.RowRange:
                        case r.ListBox:
                            return new t.CellRange(n.row, 0, n.row2, this._g.columns.length - 1)
                    }
                    return n
                }, u
            }();
            t._SelectionHandler = u
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function i(n) {
                    this._g = n;
                    n.addEventListener(n.hostElement, 'keypress', this._keypress.bind(this));
                    n.addEventListener(n.hostElement, 'keydown', this._keydown.bind(this))
                }
                return i.prototype._keydown = function(i) {
                    var r = this._g,
                        e = r.selection,
                        f = i.ctrlKey || i.metaKey,
                        u = i.shiftKey,
                        o = !0,
                        a, l;
                    if (r.isRangeValid(e) && !i.defaultPrevented) {
                        if (!r.activeEditor && i.target instanceof HTMLInputElement && !i.target.getAttribute('wj-part')) return;
                        if (r.activeEditor && r._edtHdl._keydown(i)) return;
                        var h = n.tryCast(r.rows[e.row], t.GroupRow),
                            c = n.tryCast(r.collectionView, 'IEditableCollectionView'),
                            s = i.keyCode;
                        if (r.autoClipboard) {
                            if (f && (s == 67 || s == 45)) {
                                if (l = new t.CellRangeEventArgs(r.cells, e), r.onCopying(l)) {
                                    a = r.getClipString();
                                    n.Clipboard.copy(a);
                                    r.onCopied(l)
                                }
                                i.stopPropagation();
                                return
                            }
                            if (f && s == 86 || u && s == 45) {
                                r.isReadOnly || (l = new t.CellRangeEventArgs(r.cells, e), r.onPasting(l) && n.Clipboard.paste(function(n) {
                                    r.setClipString(n);
                                    r.onPasted(l)
                                }));
                                i.stopPropagation();
                                return
                            }
                        }
                        if (r._rtl) switch (s) {
                            case n.Key.Left:
                                s = n.Key.Right;
                                break;
                            case n.Key.Right:
                                s = n.Key.Left
                        }
                        switch (s) {
                            case 65:
                                f ? r.select(new t.CellRange(0, 0, r.rows.length - 1, r.columns.length - 1)) : o = !1;
                                break;
                            case n.Key.Left:
                                e.isValid && e.col == 0 && h != null && !h.isCollapsed && h.hasChildren ? h.isCollapsed = !0 : this._moveSel(t.SelMove.None, f ? t.SelMove.Home : t.SelMove.Prev, u);
                                break;
                            case n.Key.Right:
                                e.isValid && e.col == 0 && h != null && h.isCollapsed ? h.isCollapsed = !1 : this._moveSel(t.SelMove.None, f ? t.SelMove.End : t.SelMove.Next, u);
                                break;
                            case n.Key.Up:
                                if (i.altKey && r._edtHdl._toggleListBox(this._g.selection)) break;
                                this._moveSel(f ? t.SelMove.Home : t.SelMove.Prev, t.SelMove.None, u);
                                break;
                            case n.Key.Down:
                                if (i.altKey && r._edtHdl._toggleListBox(this._g.selection)) break;
                                this._moveSel(f ? t.SelMove.End : t.SelMove.Next, t.SelMove.None, u);
                                break;
                            case n.Key.PageUp:
                                this._moveSel(t.SelMove.PrevPage, t.SelMove.None, u);
                                break;
                            case n.Key.PageDown:
                                this._moveSel(t.SelMove.NextPage, t.SelMove.None, u);
                                break;
                            case n.Key.Home:
                                this._moveSel(f ? t.SelMove.Home : t.SelMove.None, t.SelMove.Home, u);
                                break;
                            case n.Key.End:
                                this._moveSel(f ? t.SelMove.End : t.SelMove.None, t.SelMove.End, u);
                                break;
                            case n.Key.Tab:
                                this._moveSel(t.SelMove.None, u ? t.SelMove.PrevCell : t.SelMove.NextCell, !1);
                                break;
                            case n.Key.Enter:
                                this._moveSel(u ? t.SelMove.Prev : t.SelMove.Next, t.SelMove.None, !1);
                                !u && c && c.currentEditItem != null && r._edtHdl._commitRowEdits();
                                break;
                            case n.Key.Escape:
                                c && (c.currentEditItem != null && c.cancelEdit(), c.currentAddItem != null && c.cancelNew());
                                r._mouseHdl.resetMouseState();
                                break;
                            case n.Key.Delete:
                                o = this._deleteSel();
                                break;
                            case n.Key.F2:
                                o = r.startEditing(!0);
                                break;
                            case n.Key.F4:
                                o = r._edtHdl._toggleListBox(this._g.selection);
                                break;
                            case n.Key.Space:
                                o = r.startEditing(!0);
                                o && setTimeout(function() {
                                    var t = r.activeEditor;
                                    t && (t.type == 'checkbox' ? (t.checked = !t.checked, r.finishEditing()) : n.setSelectionRange(t, t.value.length))
                                });
                                break;
                            default:
                                o = !1
                        }
                        o && (i.preventDefault(), i.stopPropagation())
                    }
                }, i.prototype._keypress = function(t) {
                    var i = this._g;
                    i.activeEditor ? i._edtHdl._keypress(t) : t.charCode > n.Key.Space && i.startEditing(!1) && i.activeEditor && setTimeout(function() {
                        var r = i.activeEditor;
                        r && r.type != 'checkbox' && (r.value = String.fromCharCode(t.charCode), n.setSelectionRange(r, 1), r.dispatchEvent(i._edtHdl._evtInput), i._edtHdl._keypress(t))
                    });
                    t.stopPropagation()
                }, i.prototype._moveSel = function(n, i, r) {
                    this._g.selectionMode != t.SelectionMode.None && this._g._selHdl.moveSelection(n, i, r)
                }, i.prototype._deleteSel = function() {
                    var i = this._g,
                        f = n.tryCast(i.collectionView, 'IEditableCollectionView'),
                        u = i.selection,
                        o = i.rows,
                        e = [],
                        h, a, r, s, c, l;
                    if (i.allowDelete && !i.isReadOnly && (f == null || f.canRemove && !f.isAddingNew && !f.isEditingItem)) switch (i.selectionMode) {
                        case t.SelectionMode.CellRange:
                            if (u.leftCol == 0 && u.rightCol == i.columns.length - 1)
                                for (r = u.topRow; r > -1 && r <= u.bottomRow; r++) e.push(o[r]);
                            break;
                        case t.SelectionMode.ListBox:
                            for (r = 0; r < o.length; r++) o[r].isSelected && e.push(o[r]);
                            break;
                        case t.SelectionMode.Row:
                            u.topRow > -1 && e.push(o[u.topRow]);
                            break;
                        case t.SelectionMode.RowRange:
                            for (r = u.topRow; r > -1 && r <= u.bottomRow; r++) e.push(o[r])
                    }
                    if (e.length > 0) {
                        for (f && f.beginUpdate(), i.beginUpdate(), h = new t.CellRange, a = new t.CellRangeEventArgs(i.cells, h), r = e.length - 1; r >= 0; r--) {
                            s = e[r];
                            h.row = h.row2 = s.index;
                            i.onDeletingRow(a);
                            a.cancel || (f && s.dataItem ? f.remove(s.dataItem) : i.rows.removeAt(s.index))
                        }
                        return i.endUpdate(), f && f.endUpdate(), i.selectionMode == t.SelectionMode.ListBox && (c = i.selection.row, c > -1 && c < i.rows.length && (i.rows[c].isSelected = !0)), i.childItemsPath && i.collectionView && i.collectionView.refresh(), !0
                    }
                    return !i.isReadOnly && e.length == 0 && u.isSingleCell && (l = i._getBindingColumn(i.cells, u.row, i.columns[u.col]), (l.required == !1 || l.required == null && l.dataType == n.DataType.String) && i.getCellData(u.row, u.col, !0) && i.startEditing(!1, u.row, u.col)) ? (i.setCellData(u.row, u.col, '', !0), i.finishEditing(!0), i.invalidate(), !0) : !1
                }, i
            }();
            t._KeyboardHandler = i
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i, f, r, u;
            (function(n) {
                n[n.None = 0] = "None";
                n[n.Columns = 1] = "Columns";
                n[n.Rows = 2] = "Rows";
                n[n.Both = 3] = "Both"
            })(t.AllowResizing || (t.AllowResizing = {}));
            i = t.AllowResizing,
                function(n) {
                    n[n.None = 0] = "None";
                    n[n.Headers = 1] = "Headers";
                    n[n.Cells = 2] = "Cells";
                    n[n.Both = 3] = "Both"
                }(t.AutoSizeMode || (t.AutoSizeMode = {}));
            f = t.AutoSizeMode,
                function(n) {
                    n[n.None = 0] = "None";
                    n[n.Columns = 1] = "Columns";
                    n[n.Rows = 2] = "Rows";
                    n[n.Both = 3] = "Both"
                }(t.AllowDragging || (t.AllowDragging = {}));
            r = t.AllowDragging;
            u = function() {
                function u(t) {
                    var r = this,
                        i = t.hostElement,
                        u, f;
                    this._g = t;
                    t.addEventListener(i, 'mousedown', function(n) {
                        n.button == 0 && (document.addEventListener('mousemove', u), document.addEventListener('mouseup', f), r._mouseDown(n))
                    });
                    u = function(n) {
                        r._mouseMove(n)
                    };
                    f = function(n) {
                        document.removeEventListener('mousemove', u);
                        document.removeEventListener('mouseup', f);
                        r._mouseUp(n)
                    };
                    t.lostFocus.addHandler(function() {
                        setTimeout(function() {
                            t.containsFocus() || r.resetMouseState()
                        }, 50)
                    });
                    t.addEventListener(i, 'mousemove', this._hover.bind(this));
                    t.addEventListener(i, 'dblclick', this._dblClick.bind(this));
                    t.addEventListener(i, 'selectstart', function(n) {
                        n.target.tagName != 'INPUT' && n.preventDefault()
                    });
                    t.addEventListener(i, 'onmousewheel' in document ? 'mousewheel' : 'DOMMouseScroll', function(i) {
                        var r = t.cells.hostElement.parentElement;
                        r.scrollHeight > r.offsetHeight && (i.wheelDelta > 0 && r.scrollTop == 0 || i.wheelDelta < 0 && r.scrollTop + r.offsetHeight >= r.scrollHeight) && n.closest(i.target, '.wj-flexgrid') == t.hostElement && (i.preventDefault(), i.stopPropagation())
                    });
                    t.addEventListener(i, 'dragstart', this._dragStart.bind(this));
                    t.addEventListener(i, 'dragover', this._dragOver.bind(this));
                    t.addEventListener(i, 'dragleave', this._dragOver.bind(this));
                    t.addEventListener(i, 'drop', this._drop.bind(this));
                    t.addEventListener(i, 'dragend', this._dragEnd.bind(this));
                    this._dvMarker = n.createElement('<div class="wj-marker">&nbsp;</div>')
                }
                return u.prototype.resetMouseState = function() {
                    this._dragSource && (this._dragSource.style.opacity = 1);
                    this._showDragMarker(null);
                    var n = this._g.hostElement;
                    n && (n.style.cursor = 'default');
                    this._htDown = null;
                    this._lbSelRows = null;
                    this._szRowCol = null;
                    this._szArgs = null;
                    this._dragSource = null
                }, u.prototype._mouseDown = function(i) {
                    var u = this._g,
                        f, e, o;
                    if (!i.defaultPrevented) {
                        if (u._rcBounds = null, f = u.hitTest(i), f.cellType == t.CellType.None) {
                            u.finishEditing();
                            return
                        }
                        if ((!u.editRange || !u.editRange.contains(f.range)) && (!(i.target instanceof HTMLSelectElement) || document.activeElement != i.target)) {
                            if (this._htDown = f, this._eMouse = i, i.target == document.activeElement || u.containsFocus() || u.focus(), this._szRowCol != null) {
                                this._handleResizing(i);
                                return
                            }
                            switch (f.cellType) {
                                case t.CellType.Cell:
                                    i.ctrlKey && u.selectionMode == t.SelectionMode.ListBox && this._startListBoxSelection(f.row);
                                    this._mouseSelect(i, i.shiftKey);
                                    break;
                                case t.CellType.RowHeader:
                                    (this._g.allowDragging & r.Rows) == 0 && (i.ctrlKey && u.selectionMode == t.SelectionMode.ListBox && this._startListBoxSelection(f.row), this._mouseSelect(i, i.shiftKey))
                            }
                            if (f.cellType == t.CellType.Cell && f.col == u.columns.firstVisibleIndex && (e = n.tryCast(u.rows[f.row], t.GroupRow), e && (o = document.elementFromPoint(i.clientX, i.clientY), n.closest(o, '[' + t.CellFactory._WJA_COLLAPSE + ']')))) {
                                i.ctrlKey ? u.collapseGroupsToLevel(e.isCollapsed ? e.level + 1 : e.level) : e.isCollapsed = !e.isCollapsed;
                                this.resetMouseState();
                                i.preventDefault();
                                return
                            }
                        }
                    }
                }, u.prototype._mouseMove = function(n) {
                    if (this._htDown != null)
                        if (this._eMouse = n, this._szRowCol) this._handleResizing(n);
                        else switch (this._htDown.cellType) {
                            case t.CellType.Cell:
                                this._mouseSelect(n, !0);
                                break;
                            case t.CellType.RowHeader:
                                (this._g.allowDragging & r.Rows) == 0 && this._mouseSelect(n, !0)
                        }
                }, u.prototype._mouseUp = function(n) {
                    var i = this._htDown,
                        r, u, f;
                    !i || i.cellType != t.CellType.TopLeft || this._szArgs || n.defaultPrevented ? this._szArgs ? this._finishResizing(n) : this._handleSort(n) : (r = this._g, u = r.hitTest(n), u.panel == i.panel && u.row == i.row && u.col == i.col && (f = r.getMergedRange(i.panel, i.row, i.col) || u.range, f.row == 0 && f.col == 0 && r.select(new t.CellRange(0, 0, r.rows.length - 1, r.columns.length - 1))));
                    this.resetMouseState()
                }, u.prototype._dblClick = function(n) {
                    var r = this._g,
                        f = r.hitTest(n),
                        h = r.selection,
                        e = f.range,
                        u, o, s;
                    if (!n.defaultPrevented) {
                        if (f.edgeRight && r.allowResizing & i.Columns) {
                            if (f.cellType == t.CellType.ColumnHeader) {
                                for (n.ctrlKey && h.containsColumn(f.col) && (e = h), o = e.leftCol; o <= e.rightCol; o++)
                                    if (r.columns[o].allowResizing && (u = new t.CellRangeEventArgs(r.cells, new t.CellRange(-1, o)), r.onAutoSizingColumn(u) && r.onResizingColumn(u))) {
                                        r.autoSizeColumn(o);
                                        r.onResizedColumn(u);
                                        r.onAutoSizedColumn(u)
                                    }
                            } else if (f.cellType == t.CellType.TopLeft && r.topLeftCells.columns[f.col].allowResizing && (u = new t.CellRangeEventArgs(r.topLeftCells, new t.CellRange(-1, f.col)), r.onAutoSizingColumn(u) && r.onResizingColumn(u))) {
                                r.autoSizeColumn(f.col, !0);
                                r.onAutoSizedColumn(u);
                                r.onResizedColumn(u)
                            }
                            return
                        }
                        if (f.edgeBottom && r.allowResizing & i.Rows)
                            if (f.cellType == t.CellType.RowHeader) {
                                for (n.ctrlKey && h.containsRow(f.row) && (e = h), s = e.topRow; s <= e.bottomRow; s++)
                                    if (r.rows[s].allowResizing && (u = new t.CellRangeEventArgs(r.cells, new t.CellRange(s, -1)), r.onAutoSizingRow(u) && r.onResizingRow(u))) {
                                        r.autoSizeRow(s);
                                        r.onResizedRow(u);
                                        r.onAutoSizedRow(u)
                                    }
                            } else if (f.cellType == t.CellType.TopLeft && r.topLeftCells.rows[f.row].allowResizing && (u = new t.CellRangeEventArgs(r.topLeftCells, new t.CellRange(f.row, -1)), r.onAutoSizingRow(u) && r.onResizingRow(u))) {
                            r.autoSizeRow(f.row, !0);
                            r.onResizedRow(u);
                            r.onAutoSizedRow(u)
                        }
                    }
                }, u.prototype._hover = function(n) {
                    if (this._htDown == null) {
                        var u = this._g,
                            r = u.hitTest(n),
                            f = r.panel,
                            e = 'default';
                        this._szRowCol = null;
                        (r.cellType == t.CellType.ColumnHeader || r.cellType == t.CellType.TopLeft) && u.allowResizing & i.Columns && r.edgeRight && f.columns[r.col].allowResizing && (this._szRowCol = f.columns[r.col]);
                        (r.cellType == t.CellType.RowHeader || r.cellType == t.CellType.TopLeft) && u.allowResizing & i.Rows && r.edgeBottom && f.rows[r.row].allowResizing && (this._szRowCol = f.rows[r.row]);
                        this._szRowCol instanceof t.Column ? e = 'col-resize' : this._szRowCol instanceof t.Row && (e = 'row-resize');
                        this._szStart = this._szRowCol ? this._szRowCol.renderSize : 0;
                        u.hostElement.style.cursor = e
                    }
                }, u.prototype._mouseSelect = function(i, r) {
                    var f = this,
                        u;
                    this._htDown && this._htDown.panel && this._g.selectionMode != t.SelectionMode.None && (u = new t.HitTestInfo(this._htDown.panel, i), this._handleSelection(u, r), i.preventDefault(), n.isIE9() || (u = new t.HitTestInfo(this._g, i), u.cellType != t.CellType.Cell && u.cellType != t.CellType.RowHeader && setTimeout(function() {
                        f._mouseSelect(f._eMouse, r)
                    }, 100)))
                }, u.prototype._handleResizing = function(n) {
                    var i;
                    if (n.preventDefault(), this._szRowCol instanceof t.Column && (i = Math.max(1, this._szStart + (n.pageX - this._htDown.point.x) * (this._g._rtl ? -1 : 1)), this._szRowCol.renderSize != i)) {
                        this._szArgs == null && (this._szArgs = new t.CellRangeEventArgs(this._htDown.panel, new t.CellRange(-1, this._szRowCol.index)));
                        this._g.onResizingColumn(this._szArgs);
                        this._g.deferResizing ? this._showResizeMarker(i) : this._szRowCol.width = Math.round(i)
                    }
                    if (this._szRowCol instanceof t.Row && (i = Math.max(1, this._szStart + (n.pageY - this._htDown.point.y)), this._szRowCol.renderSize != i)) {
                        this._szArgs == null && (this._szArgs = new t.CellRangeEventArgs(this._htDown.panel, new t.CellRange(this._szRowCol.index, -1)));
                        this._g.onResizingRow(this._szArgs);
                        this._g.deferResizing ? this._showResizeMarker(i) : this._szRowCol.height = Math.round(i)
                    }
                }, u.prototype._dragStart = function(n) {
                    var u = this._g,
                        i = this._htDown,
                        f, e;
                    i && (this._dragSource = null, this._szRowCol || (f = new t.CellRangeEventArgs(u.cells, i.range), i.cellType == t.CellType.ColumnHeader && u.allowDragging & r.Columns && i.col > -1 && u.columns[i.col].allowDragging ? u.onDraggingColumn(f) && (this._dragSource = n.target) : i.cellType == t.CellType.RowHeader && u.allowDragging & r.Rows && i.row > -1 && u.rows[i.row].allowDragging && (e = u.rows[i.row], e instanceof t.GroupRow || e instanceof t._NewRowTemplate || u.onDraggingRow(f) && (this._dragSource = n.target))), this._dragSource && n.dataTransfer ? (n.dataTransfer.effectAllowed = 'move', n.dataTransfer.setData('text', ''), this._dragSource.style.opacity = .5, n.stopPropagation()) : n.preventDefault())
                }, u.prototype._dragEnd = function() {
                    this.resetMouseState()
                }, u.prototype._dragOver = function(n) {
                    var r = this._g,
                        i = r.hitTest(n),
                        u = !1;
                    this._htDown && i.cellType == this._htDown.cellType && (i.cellType == t.CellType.ColumnHeader ? u = r.columns.canMoveElement(this._htDown.col, i.col) : i.cellType == t.CellType.RowHeader && (u = r.rows.canMoveElement(this._htDown.row, i.row)));
                    u ? (n.dataTransfer.dropEffect = 'move', n.preventDefault(), this._showDragMarker(i)) : this._showDragMarker(null)
                }, u.prototype._drop = function(n) {
                    var i = this._g,
                        r = i.hitTest(n),
                        f = new t.CellRangeEventArgs(i.cells, r.range),
                        u;
                    if (this._htDown && r.cellType == this._htDown.cellType)
                        if (u = i.selection, r.cellType == t.CellType.ColumnHeader) {
                            i.columns.moveElement(this._htDown.col, r.col);
                            i.select(u.row, r.col);
                            i.onDraggedColumn(f)
                        } else if (r.cellType == t.CellType.RowHeader) {
                        i.rows.moveElement(this._htDown.row, r.row);
                        i.select(r.row, u.col);
                        i.onDraggedRow(f)
                    }
                    this.resetMouseState()
                }, u.prototype._showResizeMarker = function(i) {
                    var u = this._g,
                        f = this._dvMarker,
                        r;
                    f.parentElement || u.cells.hostElement.appendChild(f);
                    this._szRowCol instanceof t.Column ? (r = {
                        display: '',
                        left: this._szRowCol.pos + i,
                        top: 0,
                        right: '',
                        bottom: 0,
                        width: 2,
                        height: '',
                        zIndex: 1e3
                    }, u._rtl && (r.left = f.parentElement.clientWidth - r.left - r.width), this._htDown.panel.cellType == t.CellType.TopLeft && (r.left -= u.topLeftCells.hostElement.offsetWidth)) : (r = {
                        left: 0,
                        top: this._szRowCol.pos + i,
                        right: 0,
                        bottom: '',
                        width: '',
                        height: 2,
                        zIndex: 1e3
                    }, this._htDown.panel.cellType == t.CellType.TopLeft && (r.top -= u.topLeftCells.hostElement.offsetHeight));
                    n.setCss(f, r)
                }, u.prototype._showDragMarker = function(i) {
                    var f = this._g,
                        u = this._dvMarker,
                        r, e, o;
                    if (!i) {
                        u.parentElement && u.parentElement.removeChild(u);
                        this._rngTarget = null;
                        return
                    }
                    if (!i.range.equals(this._rngTarget)) {
                        this._rngTarget = i.range;
                        u.parentElement || i.panel.hostElement.appendChild(u);
                        r = {
                            display: '',
                            left: 0,
                            top: 0,
                            width: 6,
                            height: 6
                        };
                        switch (i.cellType) {
                            case t.CellType.ColumnHeader:
                                r.height = i.panel.height;
                                e = f.columns[i.col];
                                r.left = e.pos - r.width / 2;
                                i.col > this._htDown.col && (r.left += e.renderWidth);
                                f._rtl && (r.left = u.parentElement.clientWidth - r.left - r.width);
                                break;
                            case t.CellType.RowHeader:
                                r.width = i.panel.width;
                                o = f.rows[i.row];
                                r.top = o.pos - r.height / 2;
                                i.row > this._htDown.row && (r.top += o.renderHeight)
                        }
                        n.setCss(u, r)
                    }
                }, u.prototype._finishResizing = function(n) {
                    var r = this._g,
                        o = r.selection,
                        h = this._eMouse.ctrlKey,
                        i = this._szArgs,
                        u, s, f, e;
                    if (i && i.row > -1) {
                        u = i.row;
                        s = Math.max(1, this._szStart + (n.pageY - this._htDown.point.y));
                        i.panel.rows[u].height = Math.round(s);
                        r.onResizedRow(i);
                        if (h && this._htDown.cellType == t.CellType.RowHeader && o.containsRow(u))
                            for (f = o.topRow; f <= o.bottomRow; f++)
                                if (r.rows[f].allowResizing && f != u) {
                                    i = new t.CellRangeEventArgs(r.cells, new t.CellRange(f, -1));
                                    r.onResizingRow(i);
                                    if (!i.cancel) {
                                        r.rows[f].size = r.rows[u].size;
                                        r.onResizedRow(i)
                                    }
                                }
                    }
                    if (i && i.col > -1) {
                        u = i.col;
                        s = Math.max(1, this._szStart + (n.pageX - this._htDown.point.x) * (this._g._rtl ? -1 : 1));
                        i.panel.columns[u].width = Math.round(s);
                        r.onResizedColumn(i);
                        if (h && this._htDown.cellType == t.CellType.ColumnHeader && o.containsColumn(u))
                            for (e = o.leftCol; e <= o.rightCol; e++)
                                if (r.columns[e].allowResizing && e != u) {
                                    i = new t.CellRangeEventArgs(r.cells, new t.CellRange(-1, e));
                                    r.onResizingColumn(i);
                                    if (!i.cancel) {
                                        r.columns[e].size = r.columns[u].size;
                                        r.onResizedColumn(i)
                                    }
                                }
                    }
                }, u.prototype._startListBoxSelection = function(n) {
                    var i = this._g.rows,
                        t;
                    for (this._lbSelState = !i[n].isSelected, this._lbSelRows = {}, t = 0; t < i.length; t++) i[t].isSelected && (this._lbSelRows[t] = !0)
                }, u.prototype._handleSelection = function(n, i) {
                    var u = this._g,
                        o = u.rows,
                        e = u.selection,
                        f = new t.CellRange(n.row, n.col),
                        r, s, h;
                    if (n.row > -1 && n.col > -1)
                        if (this._lbSelRows != null) {
                            for (f = new t.CellRange(n.row, n.col, this._htDown.row, this._htDown.col), r = 0; r < o.length; r++)
                                if (s = f.containsRow(r) ? this._lbSelState : this._lbSelRows[r] != null, s != o[r].isSelected && (h = new t.CellRangeEventArgs(u.cells, new t.CellRange(r, e.col, r, e.col2)), u.onSelectionChanging(h))) {
                                    o[r].isSelected = s;
                                    u.onSelectionChanged(h)
                                }
                            u.scrollIntoView(n.row, n.col)
                        } else n.cellType == t.CellType.RowHeader && (f.col = 0, f.col2 = u.columns.length - 1), i && (f.row2 = e.row2, f.col2 = e.col2), u.select(f)
                }, u.prototype._handleSort = function(i) {
                    var u = this._g,
                        o = u.collectionView,
                        r = u.hitTest(i),
                        s, a, h, e;
                    if (this._htDown && r.cellType == this._htDown.cellType && r.col == this._htDown.col && r.cellType == t.CellType.ColumnHeader && !r.edgeRight && r.col > -1 && o && o.canSort && u.allowSorting) {
                        var c = u.getMergedRange(r.panel, r.row, r.col),
                            v = c ? c.row2 : r.row,
                            l = u.columns[r.col],
                            f = u._getBindingColumn(r.panel, r.row, l);
                        if ((v == u._getSortRowIndex() || l != f) && (s = f.currentSort, a = s != '+', f.allowSorting && f.binding)) {
                            if (!s && i.ctrlKey) return;
                            if (h = new t.CellRangeEventArgs(u.columnHeaders, new t.CellRange(-1, r.col)), u.onSortingColumn(h)) {
                                e = o.sortDescriptions;
                                i.ctrlKey ? e.clear() : e.splice(0, e.length, new n.collections.SortDescription(f._getBindingSort(), a));
                                u.onSortedColumn(h)
                            }
                        }
                    }
                }, u
            }();
            t._MouseHandler = u
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function i(i) {
                    var r = this;
                    this._fullEdit = !1;
                    this._list = null;
                    this._g = i;
                    this._evtInput = document.createEvent('HTMLEvents');
                    this._evtInput.initEvent('input', !0, !1);
                    i.selectionChanging.addHandler(function(n, t) {
                        var u;
                        if (r.finishEditing(), u = i._selHdl._sel.row, u != t.row) {
                            var f = i.rows.length,
                                e = u > -1 && u < f ? i.rows[u].dataItem : null,
                                o = t.row > -1 && t.row < f ? i.rows[t.row].dataItem : null;
                            e != o && r._commitRowEdits()
                        }
                    });
                    i.addEventListener(i.hostElement, 'blur', this._blur.bind(this), !0);
                    i.addEventListener(i.hostElement, 'mousedown', function(u) {
                        var o = i.selection,
                            e = i.hitTest(u),
                            f, s;
                        if (r._htDown = null, r._cancelClick = !1, e.cellType != t.CellType.Cell && e.cellType != t.CellType.None) r._lbx && n.contains(r._lbx.hostElement, u.target) || r._commitRowEdits();
                        else if (e.cellType != t.CellType.None) {
                            if (f = n.tryCast(u.target, HTMLInputElement), f && f.type == 'checkbox' && n.hasClass(f.parentElement, 'wj-cell') && (f != r.activeEditor ? (r.startEditing(!1, e.row, e.col), setTimeout(function() {
                                    f = r.activeEditor;
                                    f && f.type == 'checkbox' ? (f.checked = !f.checked, f.focus(), r.finishEditing()) : r._cancelClick = !0
                                })) : r.finishEditing()), s = document.elementFromPoint(u.clientX, u.clientY), n.closest(s, '[' + t.CellFactory._WJA_DROPDOWN + ']')) {
                                r._toggleListBox(e.range);
                                r._htDown = null;
                                u.preventDefault();
                                return
                            }
                            f == null && e.row == o.row && e.col == o.col && (r._htDown = e)
                        }
                    }, !0);
                    i.addEventListener(i.hostElement, 'click', function(n) {
                        if (r._cancelClick) {
                            n.preventDefault();
                            n.stopPropagation();
                            return
                        }
                        if (r._htDown && !r.activeEditor) {
                            var t = i.hitTest(n);
                            t.range.equals(r._htDown.range) && r.startEditing(!0, t.row, t.col)
                        }
                    }, !0)
                }
                return i.prototype.startEditing = function(i, r, u, f) {
                    var e, s, h, c, l, a, o;
                    if ((i === void 0 && (i = !0), e = this._g, r = n.asNumber(r, !0, !0), u = n.asNumber(u, !0, !0), r == null && (r = e.selection.row), u == null && (u = e.selection.col), f == null && (f = !0), !this._allowEditing(r, u)) || (s = e.getMergedRange(e.cells, r, u), s || (s = new t.CellRange(r, u)), h = e.rows[r].dataItem, e.select(s, !0), !e.rows[r] || h != e.rows[r].dataItem)) return !1;
                    if (s.equals(this._rng)) return !0;
                    if (c = new t.CellRangeEventArgs(e.cells, s), !e.onBeginningEdit(c)) return !1;
                    if (l = n.tryCast(e.collectionView, 'IEditableCollectionView'), l && (h = e.rows[r].dataItem, l.editItem(h)), this._fullEdit = i, this._rng = s, this._list = null, a = e.columns[u].dataMap, a && (this._list = a.getDisplayValues()), e.refresh(!1), o = this._edt, o) {
                        o.type == 'checkbox' ? this._fullEdit = !1 : f && n.setSelectionRange(o, 0, o.value.length);
                        e.onPrepareCellForEdit(c);
                        o = this._edt;
                        o && f && o.focus()
                    }
                    return !0
                }, i.prototype.finishEditing = function(i) {
                    var u, h, l, o, s;
                    if (i === void 0 && (i = !1), u = this._edt, !u) return this._removeListBox(), !0;
                    var r = this._g,
                        f = this._rng,
                        e = new t.CellRangeEventArgs(r.cells, f),
                        c = this._g.containsFocus();
                    if (r.activeEditor && c && (h = n.Control.getControl(n.closest(document.activeElement, '.wj-control')), h)) h.onLostFocus(e);
                    e.cancel = i;
                    r.onCellEditEnding(e);
                    if (!e.cancel)
                        for (l = u.type == 'checkbox' ? u.checked : u.value, o = f.topRow; o <= f.bottomRow && o < r.rows.length; o++)
                            for (s = f.leftCol; s <= f.rightCol && s < r.columns.length; s++) r.cells.setCellData(o, s, l, !0);
                    this._edt = null;
                    this._rng = null;
                    this._list = null;
                    this._removeListBox();
                    r.refresh(!1);
                    c && !r.containsFocus() && r.focus();
                    r.onCellEditEnded(e);
                    return !0
                }, Object.defineProperty(i.prototype, "activeEditor", {
                    get: function() {
                        return this._edt
                    },
                    enumerable: !0,
                    configurable: !0
                }), Object.defineProperty(i.prototype, "editRange", {
                    get: function() {
                        return this._rng
                    },
                    enumerable: !0,
                    configurable: !0
                }), i.prototype._allowEditing = function(n, i) {
                    var r = this._g;
                    return r.isReadOnly || r.selectionMode == t.SelectionMode.None ? !1 : n < 0 || n >= r.rows.length || r.rows[n].isReadOnly || !r.rows[n].isVisible ? !1 : i < 0 || i >= r.columns.length || r.columns[i].isReadOnly || !r.columns[i].isVisible ? !1 : r._getBindingColumn(r.cells, n, r.columns[i]).isReadOnly ? !1 : !0
                }, i.prototype._commitRowEdits = function() {
                    var i, r, u;
                    if (this.finishEditing(), i = this._g, r = n.tryCast(i.collectionView, 'IEditableCollectionView'), r && r.currentEditItem) {
                        u = new t.CellRangeEventArgs(i.cells, i.selection);
                        i.onRowEditEnding(u);
                        r.commitEdit();
                        i.onRowEditEnded(u)
                    }
                }, i.prototype._blur = function() {
                    var n = this;
                    setTimeout(function() {
                        if (!n._g.containsFocus()) {
                            var t = document.activeElement;
                            t && getComputedStyle(t).position == 'fixed' || n._commitRowEdits()
                        }
                    }, 200)
                }, i.prototype._keydown = function(t) {
                    switch (t.keyCode) {
                        case n.Key.F2:
                            return this._fullEdit = !this._fullEdit, t.preventDefault(), !0;
                        case n.Key.F4:
                            return this._toggleListBox(this._g.selection), t.preventDefault(), !0;
                        case n.Key.Space:
                            var i = this._edt;
                            return i && i.type == 'checkbox' && (i.checked = !i.checked, this.finishEditing(), t.preventDefault()), !0;
                        case n.Key.Enter:
                        case n.Key.Tab:
                            return this.finishEditing(), !1;
                        case n.Key.Escape:
                            return this.finishEditing(!0), !0;
                        case n.Key.Up:
                        case n.Key.Down:
                        case n.Key.Left:
                        case n.Key.Right:
                        case n.Key.PageUp:
                        case n.Key.PageDown:
                        case n.Key.Home:
                        case n.Key.End:
                            if (this._lbx) return this._keydownListBox(t);
                            if (t.altKey) switch (t.keyCode) {
                                case n.Key.Up:
                                case n.Key.Down:
                                    return this._toggleListBox(this._g.selection), t.preventDefault(), !0
                            }
                            if (!this._fullEdit) return this.finishEditing(), !1
                    }
                    return !0
                }, i.prototype._keydownListBox = function(t) {
                    var i = !0;
                    if (this._lbx) switch (t.keyCode) {
                        case n.Key.Up:
                            t.altKey ? this._toggleListBox(this._g.selection) : this._lbx.selectedIndex > 0 && this._lbx.selectedIndex--;
                            break;
                        case n.Key.Down:
                            t.altKey ? this._toggleListBox(this._g.selection) : this._lbx.selectedIndex++;
                            break;
                        case n.Key.Home:
                        case n.Key.PageUp:
                            this._lbx.selectedIndex = 0;
                            break;
                        case n.Key.End:
                        case n.Key.PageDown:
                            this._lbx.selectedIndex = this._lbx.collectionView.items.length - 1;
                            break;
                        default:
                            i = !1
                    }
                    return i ? (t.preventDefault(), !0) : !1
                }, i.prototype._keypress = function(t) {
                    var i = this._edt,
                        f, u, r;
                    if (i && i.type != 'checkbox' && t.target == i && this._list && this._list.length > 0 && t.charCode >= 32)
                        for (f = i.selectionStart, u = i.value.substr(0, f), t.target == i && (f++, u += String.fromCharCode(t.charCode)), u = u.toLowerCase(), r = 0; r < this._list.length; r++)
                            if (this._list[r].toLowerCase().indexOf(u) == 0) {
                                i.value = this._list[r];
                                n.setSelectionRange(i, f, this._list[r].length);
                                i.dispatchEvent(this._evtInput);
                                t.preventDefault();
                                break
                            }
                }, i.prototype._toggleListBox = function(t) {
                    var i = this._g,
                        r, u;
                    return this._lbx && (this._removeListBox(), i.selection.contains(t)) ? (i.activeEditor ? i.activeEditor.focus() : i.containsFocus() || i.focus(), !0) : (r = i.isTouching, u = i._getBindingColumn(i.cells, t.row, i.columns[t.col]), !n.input || !u.dataMap || u.showDropDown === !1) ? !1 : !n.input || !this.startEditing(!0, t.row, t.col, !r) ? !1 : (this._lbx = this._createListBox(), this._lbx.showSelection(), r && this._lbx.focus(), !0)
                }, i.prototype._createListBox = function() {
                    var u = this,
                        t = this._g,
                        r = this._rng,
                        o = t.rows[r.row],
                        e = t._getBindingColumn(t.cells, r.row, t.columns[r.col]),
                        f = document.createElement('div'),
                        i = new n.input.ListBox(f);
                    return n.addClass(f, 'wj-dropdown-panel'), i.maxHeight = o.renderHeight * 4, i.itemsSource = e.dataMap.getDisplayValues(), i.selectedValue = t.activeEditor ? t.activeEditor.value : t.getCellData(r.row, r.col, !0), n.addClass(f, e.dropDownCssClass), i.addEventListener(i.hostElement, 'click', function() {
                        u._removeListBox();
                        u.finishEditing()
                    }), i.lostFocus.addHandler(function() {
                        u._removeListBox()
                    }), i.selectedIndexChanged.addHandler(function() {
                        var i = t.activeEditor;
                        i && (i.value = u._lbx.selectedValue, i.dispatchEvent(u._evtInput), n.setSelectionRange(i, 0, i.value.length))
                    }), n.showPopup(f, t.getCellBoundingRect(r.row, r.col)), i
                }, i.prototype._removeListBox = function() {
                    this._lbx && (n.hidePopup(this._lbx.hostElement, !0), this._lbx.dispose(), this._lbx = null)
                }, i
            }();
            t._EditHandler = i
        })(t = n.grid || (n.grid = {}))
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
            var r = function() {
                    function r(n) {
                        this._nrt = new i;
                        this._g = n;
                        n.beginningEdit.addHandler(this._beginningEdit, this);
                        n.pastingCell.addHandler(this._beginningEdit, this);
                        n.rowEditEnded.addHandler(this._rowEditEnded, this);
                        n.loadedRows.addHandler(this.updateNewRowTemplate, this)
                    }
                    return r.prototype.updateNewRowTemplate = function() {
                        var e = n.tryCast(this._g.collectionView, 'IEditableCollectionView'),
                            u = this._g,
                            i = u.rows,
                            o = e && e.canAddNew && u.allowAddNew && !u.isReadOnly,
                            r = i.indexOf(this._nrt),
                            f;
                        !o && r > -1 ? (f = u.selection, f.row == r && u.select(f.row - 1, f.col), i.removeAt(r)) : o && (r < 0 ? i.push(this._nrt) : r != i.length - 1 && (i.removeAt(r), i.push(this._nrt)), this._nrt && this._nrt._setFlag(t.RowColFlags.ParentCollapsed, !1))
                    }, r.prototype._beginningEdit = function(t, r) {
                        var f, u, e;
                        if (!r.cancel && (f = this._g.rows[r.row], n.tryCast(f, i) && (u = n.tryCast(this._g.collectionView, 'IEditableCollectionView'), u && u.canAddNew))) {
                            e = u.currentAddItem && u.currentAddItem == f.dataItem ? u.currentAddItem : u.addNew();
                            u.moveCurrentTo(e);
                            this.updateNewRowTemplate();
                            this._g.refresh(!0);
                            this._g.onRowAdded(r);
                            r.cancel && u.cancelNew()
                        }
                    }, r.prototype._rowEditEnded = function() {
                        var t = n.tryCast(this._g.collectionView, 'IEditableCollectionView');
                        t && t.isAddingNew && t.commitNew()
                    }, r
                }(),
                i;
            t._AddNewHandler = r;
            i = function(n) {
                function t() {
                    n.apply(this, arguments)
                }
                return __extends(t, n), t
            }(t.Row);
            t._NewRowTemplate = i
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {})),
    function(n) {
        var t;
        (function(t) {
            'use strict';
            var i = function() {
                function t(t) {
                    this._tbx = n.createElement('<input class="wj-grid-editor wj-form-control" wj-part="ime-target"/>');
                    this._cssHidden = {
                        opacity: '0',
                        pointerEvents: 'none',
                        position: 'absolute',
                        left: -10,
                        top: -10,
                        width: 0
                    };
                    n.setCss(this._tbx, this._cssHidden);
                    this._g = t;
                    this._g.cells.hostElement.parentElement.appendChild(this._tbx);
                    this._updateImeFocus();
                    t.addEventListener(this._tbx, 'compositionstart', this._compositionstart.bind(this));
                    t.cellEditEnded.addHandler(this._cellEditEnded, this);
                    t.addEventListener(t.hostElement, 'blur', this._updateImeFocus.bind(this), !0);
                    t.addEventListener(t.hostElement, 'focus', this._updateImeFocus.bind(this), !0);
                    t.selectionChanged.addHandler(this._updateImeFocus, this)
                }
                return t.prototype.dispose = function() {
                    var n = this._g;
                    n.removeEventListener(this._tbx, 'compositionstart');
                    n.cellEditEnded.removeHandler(this._cellEditEnded);
                    n.removeEventListener(n.hostElement, 'blur');
                    n.removeEventListener(n.hostElement, 'focus');
                    n.selectionChanged.removeHandler(this._updateImeFocus);
                    this._tbx.parentElement && this._tbx.parentElement.removeChild(this._tbx)
                }, t.prototype._cellEditEnded = function() {
                    n.setCss(this._tbx, this._cssHidden);
                    this._tbx.value = ''
                }, t.prototype._compositionstart = function() {
                    var t = this._g,
                        i;
                    if (t.activeEditor == null && (i = t._selHdl.selection, t.startEditing(!1, i.row, i.col, !1))) {
                        var r = t.getCellBoundingRect(i.row, i.col),
                            u = t.scrollPosition,
                            f = t.cells.hostElement;
                        n.setCss(this._tbx, {
                            opacity: '',
                            pointerEvents: '',
                            left: t.columns[i.col].pos + u.x + f.offsetLeft,
                            top: t.rows[i.row].pos + u.y + f.offsetTop,
                            width: r.width - 1,
                            height: r.height - 1
                        });
                        t._edtHdl._edt = this._tbx
                    }
                }, t.prototype._updateImeFocus = function() {
                    var t = this._g,
                        n, i;
                    !t.containsFocus() || t.activeEditor || t.isTouching || (n = this._tbx, this._enableIme() ? (n.disabled = !1, n.select()) : n.disabled || (n.disabled = !0, i = document.activeElement, i instanceof HTMLElement && i.blur(), t.focus()))
                }, t.prototype._enableIme = function() {
                    var i = this._g,
                        t = i.selection;
                    return t.row < 0 || t.col < 0 || !i._edtHdl._allowEditing(t.row, t.col) ? !1 : i.columns[t.col].dataType == n.DataType.Boolean ? !1 : !0
                }, t
            }();
            t._ImeHandler = i
        })(t = n.grid || (n.grid = {}))
    }(wijmo || (wijmo = {}))