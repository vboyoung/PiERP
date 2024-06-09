# PiERP

## gridEvent

####### clearGridData : 해당 그리드의 데이터를 클리어, $('#gridId').clearGridData();

####### onSelectRow : 행이 원클릭 되었을때 발생, onSelectRow : function( rowid, status ) {...},

####### ondblClickRow : 행이 더블클릭 되었을때 발생

## gridMethod

##### getGridParam : grid 선택시 rowid 반환, var selRow = $('#list').getGridParam('selrow');

##### getRowData : rowid에 해당하는 행의 데이터 객체를 반환. 없는 경우 공백 객체를 반환

##### setRowData : rowid에 해당하는 행에 데이터 세팅, $('#list2').setRowData( 1, {stateFlag:'U'} );

##### getCell : cell value 반환, var audtPrgsStatCd = $('#list').getCell( selRow, 'audtPrgsStatCd');

##### setCell : cell value set, $('#list').setCell(selRow, 'marAlcAmt', 0);
