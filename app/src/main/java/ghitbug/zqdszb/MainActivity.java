package ghitbug.zqdszb;

import android.os.Bundle;

import ghitbug.zqdszb.entity.HotChannelBean;
import ghitbug.zqdszb.library.api.HttpPostService;
import ghitbug.zqdszb.library.ui.BaseMVPActivity;

public class MainActivity extends BaseMVPActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		presenter.sendPost(HttpPostService.LIVE_HOT_CHANNEL,null,HotChannelBean.class,false,false,null);
	}
}
