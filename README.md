## PiERP

## pierp.ui.components 에서 공통함수 확인

### getGridParam('selrow') vs getGridParam('data') 차이 있는지 확인필요!

### gridEvent

###### clearGridData - 해당 그리드의 데이터를 clear : $('#gridId').clearGridData();

###### onSelectRow - 행이 원클릭 되었을때 발생 : onSelectRow : function( rowid, status ) {...},

###### ondblClickRow - 행이 더블클릭 되었을때 발생

###### afterSaveCell - 저장 후

###### validEditCell - 수정 불가, 수정 가능 체크

### gridMethod

###### getGridParam - grid 선택시 rowid 반환 : var selRow = $('#list').getGridParam('selrow');

###### getRowData - rowid에 해당하는 행의 데이터 객체를 반환. 없는 경우 공백 객체를 반환

###### setRowData - rowid에 해당하는 행에 데이터 세팅 : $('#list2').setRowData( 1, {stateFlag:'U'} );

###### getCell - cell value 반환 : var audtPrgsStatCd = $('#list').getCell( selRow, 'audtPrgsStatCd');

###### setCell - cell value set : $('#list').setCell(selRow, 'marAlcAmt', 0);

### 공통검색 팝업 호출

##### makeGridSearchEvent - grid cell 선택시 팝업 호출

##### setSearchHandler - input 입력 시 팝업 호출

### 업무 팝업 호출

##### cfn_PopUpModule - 업무화면 popup 호출 시
