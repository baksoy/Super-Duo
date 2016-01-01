package barqsoft.footballscores;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by beraaksoy on 12/21/15.
 */
public class ScoresWidgetRemoteViewsService extends RemoteViewsService {

    private static final String CONTENT_AUTHORITY = "barqsoft.footballscores";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private interface QueryParam {

        String[] PROJECTION = {
                "league",
                "home",
                "away",
                "home_goals",
                "away_goals",
                "match_day",
                "match_id",
                "time"
        };

        int LEAGUE = 0;
        int HOME = 1;
        int AWAY = 2;
        int HOME_GOALS = 3;
        int AWAY_GOALS = 4;
        int MATCH_DAY = 5;
        int MATCH_ID = 6;
        int MATCH_TIME = 7;
    }

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsService.RemoteViewsFactory() {
            private Cursor mCursor = null;

            @Override
            public void onCreate() {
            }

            //Method called by the App Widget Host
            @Override
            public void onDataSetChanged() {
                if (mCursor != null) {
                    mCursor.close();
                }

                final long identityToken = Binder.clearCallingIdentity();
                Uri uri = BASE_CONTENT_URI.buildUpon().appendPath("date").build();

                String formatString = getString(R.string.date_format_ymd);
                SimpleDateFormat format = new SimpleDateFormat(formatString);
                String todayDate = format.format(new Date());

                mCursor = getContentResolver().query(uri,
                        QueryParam.PROJECTION,
                        null,
                        new String[]{todayDate},
                        null);

                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (mCursor != null) {
                    mCursor.close();
                    mCursor = null;
                }
            }

            @Override
            public int getCount() {
                return mCursor == null ? 0 : mCursor.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        mCursor == null || !mCursor.moveToPosition(position)) {
                    return null;
                }
                final RemoteViews views = new RemoteViews(getPackageName(), R.layout.scores_list_widget_item);

                String league = getString(Utilities.getLeague(mCursor.getInt(QueryParam.LEAGUE)));

                String matchDay = getString(R.string.match_day, mCursor.getString(QueryParam.MATCH_DAY));
                String matchTime = mCursor.getString(QueryParam.MATCH_TIME);

                String homeTeamName = mCursor.getString(QueryParam.HOME);
                String awayTeamName = mCursor.getString(QueryParam.AWAY);
                String score = Utilities.getScores(mCursor.getInt(QueryParam.HOME_GOALS), mCursor.getInt(QueryParam.AWAY_GOALS));

                views.setTextViewText(R.id.widget_item_league, league);
                views.setTextViewText(R.id.widget_item_home_name, homeTeamName);
                views.setTextViewText(R.id.widget_item_away_name, awayTeamName);
                views.setTextViewText(R.id.widget_item_score, score);
                views.setTextViewText(R.id.widget_item_matchday, matchDay);
                views.setTextViewText(R.id.widget_item_matchtime, matchTime);

                views.setImageViewResource(R.id.widget_item_home_crest, Utilities.getTeamCrestByTeamName(homeTeamName));
                views.setImageViewResource(R.id.widget_item_away_crest, Utilities.getTeamCrestByTeamName(awayTeamName));

                views.setContentDescription(R.id.widget_item_home_crest, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_crest, awayTeamName);
                views.setContentDescription(R.id.widget_item_home_name, homeTeamName);
                views.setContentDescription(R.id.widget_item_away_name, awayTeamName);
                views.setTextViewText(R.id.widget_item_league, league);
                views.setContentDescription(R.id.widget_item_matchday, matchDay);
                views.setContentDescription(R.id.widget_item_score, score);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.scores_list_widget_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (mCursor.moveToPosition(position))
                    return mCursor.getLong(QueryParam.MATCH_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
