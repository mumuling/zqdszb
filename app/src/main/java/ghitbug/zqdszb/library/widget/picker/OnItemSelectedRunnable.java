package ghitbug.zqdszb.library.widget.picker;


import ghitbug.zqdszb.library.widget.picker.view.WheelView;

public final class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    public OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
