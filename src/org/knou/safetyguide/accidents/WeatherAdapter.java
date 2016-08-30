package org.knou.safetyguide.accidents;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.knou.safetyguide.R;
import org.knou.safetyguide.model.Weather;

/**
 * Created by namhyeon on 2016-08-11
 * This project was created for software contest submissions.
 * Korea National Open University
 */

public final class WeatherAdapter extends ArrayAdapter<Weather> {

    private final int newsItemLayoutResource;

    public WeatherAdapter(final Context context, final int weatherItemLayoutResource) {
        super(context, 0);
        this.newsItemLayoutResource = weatherItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        // We need to get the best view (re-used if possible) and then
        // retrieve its corresponding ViewHolder, which optimizes lookup efficiency
        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final Weather entry = getItem(position);

        // Setting the title view is straightforward
        viewHolder.titleView.setText(entry.getName());

        // Setting the subTitle view requires a tiny bit of formatting
        String formattedSubTitle = entry.getClass1();
        if(entry.getClass2() != null) {
            formattedSubTitle += " > " + entry.getClass2();
        }
        if(entry.getClass3() != null) {
            formattedSubTitle += " > " + entry.getClass3();
        }
        viewHolder.subTitleView.setText(formattedSubTitle);
        return view;
    }

    private View getWorkingView(final View convertView) {
        // The workingView is basically just the convertView re-used if possible
        // or inflated new if not possible
        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(newsItemLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {
        // The viewHolder allows us to avoid re-looking up view references
        // Since views are recycled, these references will never change
        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.titleView = (TextView) workingView.findViewById(R.id.news_entry_title);
            viewHolder.subTitleView = (TextView) workingView.findViewById(R.id.news_entry_subtitle);
            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    /**
     * ViewHolder allows us to avoid re-looking up view references
     * Since views are recycled, these references will never change
     */
    private static class ViewHolder {
        public TextView titleView;
        public TextView subTitleView;
    }
}
