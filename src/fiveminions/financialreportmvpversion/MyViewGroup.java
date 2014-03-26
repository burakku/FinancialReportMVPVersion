package fiveminions.financialreportmvpversion;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.MeasureSpec;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class MyViewGroup extends ViewGroup {
	private Scroller scroller;
	private int distance;

	private View menu_view, content_view;
	private int duration = 500;

	private ViewTreeObserver viewTreeObserver;
	private Context context;
	private CloseAnimation closeAnimation;

	public static boolean isMenuOpned = false;

	public MyViewGroup(Context context) {
		super(context, null);
	}

	public void setCloseAnimation(CloseAnimation closeAnimation) {
		this.closeAnimation = closeAnimation;
	}

	public MyViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		scroller = new Scroller(context);
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed) {
			menu_view = getChildAt(0);// scroller view
			content_view = getChildAt(1);// mainpage view

			// fill_parent
			content_view.measure(0, 0);
			content_view.layout(0, 0, getWidth(), getHeight());
		}
	}

	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();// refresh
			if (closeAnimation != null)
				closeAnimation.closeMenuAnimation();
		}
	}

	void showMenu() {
		isMenuOpned = true;
		scroller.startScroll(getScrollX(), 0, -distance, 0, duration);
		invalidate();// refresh
	}

	void closeMenu() {
		isMenuOpned = false;
		scroller.startScroll(getScrollX(), 0, distance, 0, duration);

		invalidate();// refresh
	}

	void closeMenu_1() {
		isMenuOpned = false;
		scroller.startScroll(getScrollX(), 0, distance - getWidth(), 0,
				duration);
		invalidate();// refresh
	}

	void closeMenu_2() {
		isMenuOpned = false;
		scroller.startScroll(getScrollX(), 0, getWidth(), 0, duration);
		invalidate();// refresh
	}

	/***
	 * Menu startScroll(startX, startY, dx, dy)
	 * 
	 */
	void slidingMenu() {

		// less than half page
		if (getScrollX() > -getWidth() / 2) {
			scroller.startScroll(getScrollX(), 0, -getScrollX(), 0, duration);
			isMenuOpned = false;
		}
		// more than half page
		else if (getScrollX() <= -getWidth() / 2) {
			scroller.startScroll(getScrollX(), 0, -(distance + getScrollX()),
					0, duration);
			isMenuOpned = true;
		}

		invalidate();// refresh
		Log.v("cw", "getScrollX()=" + getScrollX());
	}
}

abstract class CloseAnimation {

	public void closeMenuAnimation() {

	};
}
