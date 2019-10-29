package org.zongf.wx.power.nation.vo.ocr;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author: zongf
 * @created: 2019-10-24
 * @since 1.0
 */
public class OcrResponse {

	@JSONField(serialize = false)
    private long log_id;

	@JSONField(serialize = false)
	private String error_code;

    private int words_result_num;

    private List<TextArea> words_result;

	public OcrResponse() {
        super();
    }

	public OcrResponse(long log_id, int words_result_num, List<TextArea> words_result) {
        super();
		this.log_id = log_id;
		this.words_result_num = words_result_num;
		this.words_result = words_result;
    }

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public void setLog_id(long log_id){
		this.log_id=log_id;
	}

	public long getLog_id(){
		return this.log_id;
	}

    public void setWords_result_num(int words_result_num){
		this.words_result_num=words_result_num;
	}

	public int getWords_result_num(){
		return this.words_result_num;
	}

    public void setWords_result(List<TextArea> words_result){
		this.words_result=words_result;
	}

	public List<TextArea> getWords_result(){
		return this.words_result;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {log_id:" + log_id + ", words_result_num:" + words_result_num + ", words_result:" + words_result  + "}";
	}

}
