package kr.pe.ekxkaks.web2m.common;

/**
 * 요청을 받는 모델
 * @author ekxkaks
 *
 */
public class RequestQuery {
    /**
     * 게시판번호
     */
    String sort;
    String sub_sort;
    String page;
    String startpage;
    String keyfield;
    String key_bs;
    /**
     * 커뮤니티 아이디 : dokkaebi
     */
    String p1;
    String p2;
    String p3;
    /**
     * 게시글 번호 
     */
    String number;
    /**
     * 호출화면유형 : view / preedit
     */
    String mode;
    String div;
	/**
	 * 필드 {@link #sort}의 값을 반환한다.
	 * @return {@link #sort}의 값
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #sort}의 값을 지정한다.
	 * @param sort 필드 {@link #sort}의 값
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * 필드 {@link #sub_sort}의 값을 반환한다.
	 * @return {@link #sub_sort}의 값
	 */
	public String getSub_sort() {
		return sub_sort;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #sub_sort}의 값을 지정한다.
	 * @param sub_sort 필드 {@link #sub_sort}의 값
	 */
	public void setSub_sort(String sub_sort) {
		this.sub_sort = sub_sort;
	}
	/**
	 * 필드 {@link #page}의 값을 반환한다.
	 * @return {@link #page}의 값
	 */
	public String getPage() {
		return page;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #page}의 값을 지정한다.
	 * @param page 필드 {@link #page}의 값
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * 필드 {@link #startpage}의 값을 반환한다.
	 * @return {@link #startpage}의 값
	 */
	public String getStartpage() {
		return startpage;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #startpage}의 값을 지정한다.
	 * @param startpage 필드 {@link #startpage}의 값
	 */
	public void setStartpage(String startpage) {
		this.startpage = startpage;
	}
	/**
	 * 필드 {@link #keyfield}의 값을 반환한다.
	 * @return {@link #keyfield}의 값
	 */
	public String getKeyfield() {
		return keyfield;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #keyfield}의 값을 지정한다.
	 * @param keyfield 필드 {@link #keyfield}의 값
	 */
	public void setKeyfield(String keyfield) {
		this.keyfield = keyfield;
	}
	/**
	 * 필드 {@link #key_bs}의 값을 반환한다.
	 * @return {@link #key_bs}의 값
	 */
	public String getKey_bs() {
		return key_bs;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #key_bs}의 값을 지정한다.
	 * @param key_bs 필드 {@link #key_bs}의 값
	 */
	public void setKey_bs(String key_bs) {
		this.key_bs = key_bs;
	}
	/**
	 * 필드 {@link #p1}의 값을 반환한다.
	 * @return {@link #p1}의 값
	 */
	public String getP1() {
		return p1;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #p1}의 값을 지정한다.
	 * @param p1 필드 {@link #p1}의 값
	 */
	public void setP1(String p1) {
		this.p1 = p1;
	}
	/**
	 * 필드 {@link #p2}의 값을 반환한다.
	 * @return {@link #p2}의 값
	 */
	public String getP2() {
		return p2;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #p2}의 값을 지정한다.
	 * @param p2 필드 {@link #p2}의 값
	 */
	public void setP2(String p2) {
		this.p2 = p2;
	}
	/**
	 * 필드 {@link #p3}의 값을 반환한다.
	 * @return {@link #p3}의 값
	 */
	public String getP3() {
		return p3;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #p3}의 값을 지정한다.
	 * @param p3 필드 {@link #p3}의 값
	 */
	public void setP3(String p3) {
		this.p3 = p3;
	}
	/**
	 * 필드 {@link #number}의 값을 반환한다.
	 * @return {@link #number}의 값
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #number}의 값을 지정한다.
	 * @param number 필드 {@link #number}의 값
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 필드 {@link #mode}의 값을 반환한다.
	 * @return {@link #mode}의 값
	 */
	public String getMode() {
		return mode;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #mode}의 값을 지정한다.
	 * @param mode 필드 {@link #mode}의 값
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	/**
	 * 필드 {@link #div}의 값을 반환한다.
	 * @return {@link #div}의 값
	 */
	public String getDiv() {
		return div;
	}
	/**
	 * 지정한 파라메타로 필드 {@link #div}의 값을 지정한다.
	 * @param div 필드 {@link #div}의 값
	 */
	public void setDiv(String div) {
		this.div = div;
	}


}
