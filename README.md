주요 API 설명

1) GET /listTables: 현재 연결된 MySQL 스키마의 모든 테이블명을 배열로 반환.

    예: ["users", "orders", "products"]


2) GET /getColumns?table={tableName}: 특정 테이블의 컬럼 메타데이터를 반환.

    반환되는 필드: name, dataType, isNullable, defaultValue, maxLength, numericPrecision, numericScale

    예: [{"name":"id","dataType":"bigint","isNullable":false,...}, ...]

<br><br>
*환경변수 설정<br><br>
인텔리제이 기준<br>
실행 -> 구성편집 -> 빌드 및 실행 탭에서 <옵션수정> -> 환경변수 체크 -> DB_HOST=localhost;DB_USER=사용자명;DB_PASS=비밀번호;DB_NAME=데이터베이스명;PORT=8080 입력



<br><br>
확장 가능성<br><br>
특정 테이블의 실제 레코드 조회(페이징/정렬/필터), 단건 조회, 샘플 데이터 프리뷰 등으로 확장 가능.
두 API는 각각 테이블 목록 조회와 컬럼 스키마 조회를 담당하며, 이후 실제 데이터 조회/검색 기능을 얹는 식으로 자연스럽게 확장가능
