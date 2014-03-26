package fiveminions.financialreportmvpversion;

import model.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UserHomePage1 extends Activity implements OnClickListener,
		GestureDetector.OnGestureListener {

	private User user;
	private MyViewGroup myViewGroup;
	private LayoutInflater layoutInflater;
	private View menu_view, content_view;
	private ImageView iv_button;
	private int iv_button_width, window_windth;
	private ListView lv_menu;
	private boolean hasMeasured = false;
	private int distance;
	private GestureDetector gestureDetector;
	private boolean isScrolling = false;
	private boolean direction = false;// true, left£¬false£¬right
	private String TAG = "cw";

	private View click_view;

	private String title[] = { "Home", "Bank Account", "Transaction",
			"Monthly Spending Report", "Monthly Income Report",
			"Cash Flow Report", "Logout" };
	private ViewTreeObserver viewTreeObserver;

	void InitView() {
		gestureDetector = new GestureDetector(this);
		myViewGroup = (MyViewGroup) findViewById(R.id.vg_main);
		layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		menu_view = layoutInflater.inflate(R.layout.menu, null);
		content_view = layoutInflater.inflate(R.layout.content, null);
		myViewGroup.addView(menu_view);
		myViewGroup.addView(content_view);
		iv_button = (ImageView) content_view.findViewById(R.id.iv_button);
		lv_menu = (ListView) menu_view.findViewById(R.id.lv_menu);
		lv_menu.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
				R.id.tv_item, title));
		setListViewHeightBaseOnChildren(lv_menu);
		iv_button.setOnClickListener(this);
		getMAX_WIDTH();

		content_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (myViewGroup.getScrollX() <= -distance) {
					myViewGroup.closeMenu();
				}

			}
		});

		lv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				myViewGroup.setCloseAnimation(new CloseAnimation() {

					@Override
					public void closeMenuAnimation() {
						if (myViewGroup.getScrollX() == -window_windth)
							myViewGroup.closeMenu_2();
					}

				});
				myViewGroup.closeMenu_1();
				switch (position) {
				case 0:
					Toast.makeText(UserHomePage1.this, title[position], 1)
							.show();
					break;
				case 1:
					Intent i1 = new Intent();
					i1.setClass(UserHomePage1.this, AccountPageActivity.class);
					i1.putExtra("userid", user.getUserID());
					startActivity(i1);
					break;
				case 2:
					Intent i2 = new Intent();
					i2.setClass(UserHomePage1.this, TransactionActivity.class);
					i2.putExtra("userid", user.getUserID());
					startActivity(i2);
					break;
				case 3:
					Intent i3 = new Intent();
					i3.setClass(UserHomePage1.this,
							SpendingReportActivity.class);
					i3.putExtra("userid", user.getUserID());
					startActivity(i3);
					break;
				case 4:
					Intent i4 = new Intent();
					i4.setClass(UserHomePage1.this, MonthlyIncomeActivity.class);
					i4.putExtra("userid", user.getUserID());
					startActivity(i4);
					break;
				case 5:
					Intent i5 = new Intent();
					i5.setClass(UserHomePage1.this, CashFlowActivity.class);
					i5.putExtra("userid", user.getUserID());
					startActivity(i5);
					break;
				case 6:
					Intent i6 = new Intent();
					i6.setClass(UserHomePage1.this, LoginActivity.class);
					startActivity(i6);
					break;
				}

			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("User Home page");
		setContentView(R.layout.main);
		Bundle b = getIntent().getExtras();
		user = b.getParcelable("model.User");
		InitView();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
				&& MyViewGroup.isMenuOpned) {
			myViewGroup.closeMenu();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	/***
	 * ¼ÆËãiv_buttonµÄ¿í¶È
	 * 
	 */
	public void getMAX_WIDTH() {
		viewTreeObserver = menu_view.getViewTreeObserver();
		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {

			@Override
			public boolean onPreDraw() {
				if (!hasMeasured) {
					window_windth = getWindowManager().getDefaultDisplay()
							.getWidth();
					iv_button_width = content_view.findViewById(R.id.iv_button)
							.getWidth();
					distance = window_windth - iv_button_width;
					myViewGroup.setDistance(distance);
					ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) lv_menu
							.getLayoutParams();
					layoutParams.width = distance;
					lv_menu.setLayoutParams(layoutParams);

					hasMeasured = true;
				}
				return true;
			}
		});

	}

	@Override
	public void onClick(View v) {
		if (v == iv_button) {
			if (myViewGroup.isMenuOpned)
				myViewGroup.closeMenu();
			else
				myViewGroup.showMenu();
		}

	}

	public void setListViewHeightBaseOnChildren(ListView listView) {
		ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
		layoutParams.height = getWindowManager().getDefaultDisplay()
				.getHeight();
		listView.setLayoutParams(layoutParams);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP && isScrolling) {
			myViewGroup.slidingMenu();
		}
		gestureDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onDown(MotionEvent e) {

		menu_view.measure(0, 0);
		menu_view.layout(-distance, 0, 0, getWindowManager()
				.getDefaultDisplay().getHeight());
		lv_menu.setSelection(0);

		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.v("cw", "myViewGroup.getScrollX()=" + myViewGroup.getScrollX());


		if (!MyViewGroup.isMenuOpned && distanceX > 0) {
			
		} else if (myViewGroup.getScrollX() == -distance
				&& MyViewGroup.isMenuOpned && distanceX < 0) {
			isScrolling = false;
		}

		else if (myViewGroup.getScrollX() >= -distance
				&& myViewGroup.getScrollX() <= 0) {
			isScrolling = true;
			// myViewGroup.scrollBy((int) (e1.getX() - e2.getX()), 0);
			myViewGroup.scrollBy((int) distanceX, 0);
		}

		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

}