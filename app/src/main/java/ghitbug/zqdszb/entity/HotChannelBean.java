package ghitbug.zqdszb.entity;

import java.util.List;

import ghitbug.zqdszb.library.api.entitys.BaseResult;

/**
 * Created by GH on 2018-05-10.
 */

public class HotChannelBean extends BaseResult {

	/**
	 * cibn : 2
	 * channel_id : 383
	 * channel_name : CCTV13 央视新闻
	 * count : 191
	 * rimage : ["http://s2.starschinalive.com/thumb/40/thumb@320_180.jpg"]
	 */

	private int cibn;
	private String channel_id;
	private String channel_name;
	private int count;
	private List<String> rimage;

	public int getCibn() {
		return cibn;
	}

	public void setCibn(int cibn) {
		this.cibn = cibn;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getRimage() {
		return rimage;
	}

	public void setRimage(List<String> rimage) {
		this.rimage = rimage;
	}
}
