package quardy.ye.game.com.quardy;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.widget.GameRequestDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAd;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.google.firebase.crash.FirebaseCrash;


import org.anddev.andengine.util.Base64;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.time.TimeTCPClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,BillingProcessor.IBillingHandler, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onBillingInitialized() {
		/*
		 * Called when BillingProcessor was initialized and it's ready to purchase
		 */
    }

    final String purchaselife2 = "android.test.purchased";
    final String purchaselife4 = "android.test.purchased";
    final String purchaselife6 = "android.test.purchased";
    final String purchaselifeinf = "android.test.purchased";

    final int PURCHASE2 = 2;
    final int PURCHASE4 = 4;
    final int PURCHASE6 = 6;
    final int PURCHASEinf = 100;
    int purchaseint = 0;

    boolean purchaseinfiniteflag = false;

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        if (productId.equals(purchaselife2) && purchaseint == PURCHASE2) {
            bp.consumePurchase(purchaselife2);
            gamecount += 2;
            editor.putInt("gamecount", gamecount);
            purchaseint = 0;
            editor.commit();
            updategamecount();
        } else if (productId.equals(purchaselife4) && purchaseint == PURCHASE4) {
            bp.consumePurchase(purchaselife4);
            gamecount += 4;
            purchaseint = 0;
            editor.putInt("gamecount", gamecount);
            editor.commit();
            updategamecount();
        } else if (productId.equals(purchaselife6) && purchaseint == PURCHASE6) {
            bp.consumePurchase(purchaselife6);
            gamecount += 6;
            purchaseint = 0;
            editor.putInt("gamecount", gamecount);
            editor.commit();
            updategamecount();
        } else if (productId.equals(purchaselifeinf) && purchaseint == PURCHASEinf) {

            //gamecount += 1000000;
            purchaseint = 0;
            purchaseinfiniteflag = true;
            editor.putBoolean("purchaseinfiniteflag", true);
            editor.commit();
            updategamecount();
        }


    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
		/*
		 * Called when some error occurred. See Constants class for more details
		 *
		 * Note - this includes handling the case where the user canceled the buy dialog:
		 * errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
		 */
    }

    @Override
    public void onPurchaseHistoryRestored() {
		/*
		 * Called when purchase history was restored and the list of all owned PRODUCT ID's
		 * was loaded from Google Play
		 */
    }


    public void signin()
    {
        mGoogleApiClient.connect();

    }


    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }

int STAGEE = 9999;
    @Override
    public void onBackPressed() {
        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
        final RelativeLayout operationlayout = (RelativeLayout) findViewById(R.id.opeartionlayout);
        final RelativeLayout simplicitylayout = (RelativeLayout) findViewById(R.id.simpicitylayout);
        final RelativeLayout sortinglayout = (RelativeLayout) findViewById(R.id.sortingrelative);
        final RelativeLayout coloerdeclayout = (RelativeLayout) findViewById(R.id.colordeceptionlayout);
        final RelativeLayout pickcolorlayout = (RelativeLayout) findViewById(R.id.pickcolorlayout);
        final RelativeLayout memeorylayout = (RelativeLayout) findViewById(R.id.memorylayout);
        final RelativeLayout minesweeperlayout = (RelativeLayout) findViewById(R.id.minesweeperlayout);
        final RelativeLayout circleshooterlayout = (RelativeLayout) findViewById(R.id.colorshooter);

        final Button simplicitybutton = (Button) findViewById(R.id.button15);
        final Button sortbutton = (Button) findViewById(R.id.buttonsorti);

        final CircularProgressBar timecircularsort = (CircularProgressBar) findViewById(R.id.circularprogressbarsorting);
        final Button backsort = (Button) findViewById(R.id.buttonbacksort);
        final LinearLayout startrelsort = (LinearLayout) findViewById(R.id.StartRelLayoutsorting);
        final RelativeLayout sortsixchioce = (RelativeLayout) findViewById(R.id.sixchoice);
        final RelativeLayout gridsort = (RelativeLayout) findViewById(R.id.grid12sorting);

        final Button colordecbutton = (Button) findViewById(R.id.buttoncolodec);
        final Button pickcolorbutton = (Button) findViewById(R.id.buttonpickcolor);


        final Button memeorybutton = (Button) findViewById(R.id.buttonmemory);
        final Button minesweeper = (Button) findViewById(R.id.button16minesweeper);
        final Button colorshooterbut = (Button) findViewById(R.id.buttonshootercol);

        final Button crushbutton = (Button) findViewById(R.id.buttonshootercol2);

        final Button cardmathcalc = (Button) findViewById(R.id.buttoncardmem);

        final RelativeLayout mathcalrelative = (RelativeLayout) findViewById(R.id.cardcalculation);

        final Button pettrivia = (Button) findViewById(R.id.buttonpettrvia);
        final RelativeLayout pettrivialayout = (RelativeLayout) findViewById(R.id.pettrivialayout);

        final Button Bstartcolordec = (Button) findViewById(R.id.button31colordec);
        final CircularProgressBar colordeccirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarcolordec);
        final RelativeLayout startrelcolordec = (RelativeLayout) findViewById(R.id.StartRelLayoutcolordec);
        final RelativeLayout buttonframecolordec = (RelativeLayout) findViewById(R.id.linearLayout2);

        final RelativeLayout buttonframecolordec2 = (RelativeLayout) findViewById(R.id.Buttonframe4);
        final FrameLayout buttonframecolordec3 = (FrameLayout) findViewById(R.id.buttonframe3);
        final RelativeLayout gridcolordec = (RelativeLayout) findViewById(R.id.grid1colordec);
        final Button backbuttoncolordec = (Button) findViewById(R.id.buttonbackcolordec);

        final Button backbuttonpickcolor = (Button) findViewById(R.id.buttonbackpickcolor);
        final Button Bstartpickcolor = (Button) findViewById(R.id.button331pickcolor);
        final CircularProgressBar pickcolorcirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarpickcolor);
        final RelativeLayout buttonframepickcolor = (RelativeLayout) findViewById(R.id.sixchoicepickcolor);
        final RelativeLayout startrelpickcolor = (RelativeLayout) findViewById(R.id.StartRelLayoutpickcol);
        final RelativeLayout gridpickcolor = (RelativeLayout) findViewById(R.id.grid1pickcolor);

        final Button Bstartmemory = (Button) findViewById(R.id.button31memory);
        final Button backbuttonmemory = (Button) findViewById(R.id.buttonbackmemory);
        final CircularProgressBar memorycirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarmemory);

        final TableLayout buttonframememory = (TableLayout) findViewById(R.id.TableLayout03);
        final LinearLayout startrelmemory = (LinearLayout) findViewById(R.id.StartRelLayoutmemory);
        final RelativeLayout gridmemory = (RelativeLayout) findViewById(R.id.grid1memory);

        final Button backbuttonmineweeeper = (Button) findViewById(R.id.buttonbackminesweeper);
        final Button Bstaratminesweeper = (Button) findViewById(R.id.button31minesweeper);
        final CircularProgressBar minesweepercirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarminesweeper);

        final RelativeLayout buttonframeminesweeper = (RelativeLayout) findViewById(R.id.grid);
        final RelativeLayout startrelminesweeper = (RelativeLayout) findViewById(R.id.StartRelLayoutminesweeper);
        final RelativeLayout gridminesweeper = (RelativeLayout) findViewById(R.id.grid1minesweeper);

        final RelativeLayout startrelcircleshoooter = (RelativeLayout) findViewById(R.id.StartRelLayoutcircleshooter);

        final RelativeLayout gridshooter = (RelativeLayout) findViewById(R.id.grid1shooter);
        final RelativeLayout circleshooterlose = (RelativeLayout) findViewById(R.id.grid1shooter2);
        final Button backbuttoncolorshooter = (Button) findViewById(R.id.buttonbackcolorshooter);
        final GameView gameview = (GameView) findViewById(R.id.game);


        final Button Bstartcolorcalc = (Button) findViewById(R.id.button31cardcalculation);
        final RelativeLayout gridcardcalc = (RelativeLayout) findViewById(R.id.grid1cardcalculation);
        final Button backbuttoncolorcalc = (Button) findViewById(R.id.buttonbackcardcalculation);
        final CircularProgressBar cardcalculationcircular = (CircularProgressBar) findViewById(R.id.circularprogressbarcarcalculation);
        final RelativeLayout startrelcardcalculation = (RelativeLayout) findViewById(R.id.StartRelLayoutcardcalculation);
        final LinearLayout cardslayout = (LinearLayout) findViewById(R.id.cardslayout);
        final LinearLayout cardslayout2 = (LinearLayout) findViewById(R.id.cardslayout22);

        final Button Bstartpettrivia = (Button) findViewById(R.id.button312pettrivia);
        final Button backbuttonpettrivia = (Button) findViewById(R.id.buttonbackpettrivia);
        final RelativeLayout gridpettrivia = (RelativeLayout) findViewById(R.id.grid1petrivia);
        final CircularProgressBar pettriviacircular = (CircularProgressBar) findViewById(R.id.circularprogressbarpettrivia);
        final RelativeLayout startrelpettrivia = (RelativeLayout) findViewById(R.id.StartRelLayout2pettrivia);
        final RelativeLayout pettriviaquestion = (RelativeLayout) findViewById(R.id.pettriviamain);
        final Button backtocategory = (Button) findViewById(R.id.button18);
        final CircularProgressBar timecircular = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        final Button backtocategory2 = (Button) findViewById(R.id.button181);
        timecircular.setMax(GAMEPLAYTIME);
        final RelativeLayout startrel = (RelativeLayout) findViewById(R.id.StartRelLayout);
        final RelativeLayout grid1 = (RelativeLayout) findViewById(R.id.grid1);
        final RelativeLayout grid2 = (RelativeLayout) findViewById(R.id.grid12);
        final FrameLayout textfram = (FrameLayout) findViewById(R.id.textfram);
        final RelativeLayout btnframe = (RelativeLayout) findViewById(R.id.Buttonframe);

        final Button backtocategory3 = (Button) findViewById(R.id.buttonbacksimp);
        final Button backtocategorymath = (Button) findViewById(R.id.button18color);
        final Button backtocategoryshooter = (Button) findViewById(R.id.button18shooter);

        final Button Bstart2 = (Button) findViewById(R.id.button312);
        final RelativeLayout Startlayout2 = (RelativeLayout) findViewById(R.id.StartRelLayout2);
        final RelativeLayout buttonframe2 = (RelativeLayout) findViewById(R.id.buttonframe);
        final FrameLayout textfram2 = (FrameLayout) findViewById(R.id.textfram222);

        final CircularProgressBar timecircular2 = (CircularProgressBar) findViewById(R.id.circularprogressbarsimplicity);

        if (STAGEE == GAMESTAGE){


            points = 0;
            level = 0;
            score = 0;
            STAGEE = MENUSTAGE;

            tries = 0;


            if (waitTimersort != null) waitTimersort.cancel();
            if (waitTimer2 != null) waitTimer2.cancel();
            if (waitTimer != null) waitTimer.cancel();
            if (waitTimercolordec != null) waitTimercolordec.cancel();
            if (waitTimerpickcolor != null) waitTimerpickcolor.cancel();
            if (waitTimermemory != null) waitTimermemory.cancel();
            if (waitTimerminesweeper != null) waitTimerminesweeper.cancel();
            if (waitTimercircleshooter != null) waitTimercircleshooter.cancel();
            if (waitTimerpettrivia != null) waitTimerpettrivia.cancel();
            if (waitTimercardcalculation != null) waitTimercardcalculation.cancel();


            resetscore();
            startrelpickcolor.setVisibility(View.VISIBLE);


            startrelcircleshoooter.setVisibility(View.VISIBLE);
            shownzaire = false;

            gameview.setVisibility(View.INVISIBLE);
            gridshooter.setVisibility(View.INVISIBLE);
            circleshooterlose.setVisibility(View.INVISIBLE);
            circleshooterlayout.setVisibility(View.INVISIBLE);
            minesweeperlayout.setVisibility(View.INVISIBLE);
            memeorylayout.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);





            buttonframepickcolor.setVisibility(View.INVISIBLE);
            gridpickcolor.setVisibility(View.INVISIBLE);
            memorycirculatr.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            startrelmemory.setVisibility(View.VISIBLE);

            buttonframememory.setVisibility(View.INVISIBLE);
            gridmemory.setVisibility(View.INVISIBLE);

            memeorylayout.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            startrelminesweeper.setVisibility(View.VISIBLE);
            minesweepercirculatr.setVisibility(View.INVISIBLE);
            buttonframeminesweeper.setVisibility(View.INVISIBLE);
            gridminesweeper.setVisibility(View.INVISIBLE);

            minesweeperlayout.setVisibility(View.INVISIBLE);
            memeorylayout.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            startrelcardcalculation.setVisibility(View.VISIBLE);
            cardslayout2.setVisibility(View.INVISIBLE);
            cardslayout.setVisibility(View.INVISIBLE);
            gridcardcalc.setVisibility(View.INVISIBLE);

            mathcalrelative.setVisibility(View.INVISIBLE);
            circleshooterlayout.setVisibility(View.INVISIBLE);
            minesweeperlayout.setVisibility(View.INVISIBLE);
            memeorylayout.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            startrelpettrivia.setVisibility(View.VISIBLE);
            pettriviaquestion.setVisibility(View.INVISIBLE);
            gridpettrivia.setVisibility(View.INVISIBLE);
            pettriviacircular.setVisibility(View.INVISIBLE);


            pettrivialayout.setVisibility(View.INVISIBLE);
            mathcalrelative.setVisibility(View.INVISIBLE);
            circleshooterlayout.setVisibility(View.INVISIBLE);
            minesweeperlayout.setVisibility(View.INVISIBLE);
            memeorylayout.setVisibility(View.INVISIBLE);
            pickcolorlayout.setVisibility(View.INVISIBLE);
            coloerdeclayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);

            textfram.setVisibility(View.INVISIBLE);
            startrel.setVisibility(View.VISIBLE);
            grid1.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            btnframe.setVisibility(View.INVISIBLE);
            startrelsort.setVisibility(View.VISIBLE);
            timecircularsort.setVisibility(View.INVISIBLE);
            gridsort.setVisibility(View.INVISIBLE);
            sortinglayout.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortsixchioce.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            timecircular2.setVisibility(View.INVISIBLE);

            textfram2.setVisibility(View.INVISIBLE);
            Startlayout2.setVisibility(View.VISIBLE);
            grid2.setVisibility(View.INVISIBLE);
            simplicitylayout.setVisibility(View.INVISIBLE);
            sortinglayout.setVisibility(View.INVISIBLE);
            operationlayout.setVisibility(View.INVISIBLE);
            buttonframe2.setVisibility(View.INVISIBLE);


        }

        else

        {
            super.onBackPressed();

        }




    }


    int GAMESTAGE = 1;
    int MENUSTAGE = 0;

    int SORTING3BUTTONLEVEL = 3;
    int SORTING6BUTTONLEVEL = 2;
    int SORTINGMIN3 = 1;
    int SORTINGMAX3 = 10;

    int SORTINGMIN6 = 1;
    int SORTINGMAX6 = 50;

    int SORTINGMIN9 = 1;
    int SORTINGMAX9 = 100;


    int GAMEPLAYTIME = 50500;


    int mathlevel = 0;
    int colorlevel = 0;
    int score = 0;
    int delay = 300;
    int correctnum = 0;
    int wrong = 0;
    private int soundID,soundID2;
    private SoundPool soundPool;
    private SoundPool soundPool2;
    AudioManager audioManager;
    float actVolume, maxVolume, volume;
    int x1, x2, x3, x4, x5, x6, x7, x8, x9;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b1int, b2int, b3int, b4int, b5int, b6int;
    int counter = 0;
    int gamecount = 10;
    Operation operation;
    int highest = 0;
    ;
    int lowest = 0;
    int randomcounter;
    Boolean finishgame = false;
    int highestcolor = 0;
    Button barray[] = new Button[2];
    Button barray2[] = new Button[3];
    boolean shownzaire = false;
    final int gamewaittime = 1800;
    long time = 60;
    int LEVELMAX = 20000;
    int MAXGAMEPLAY = 10;
    int coin = 0;
    InterstitialAd mInterstitialAd;
    int gamestarcounter = 0;
    int[] mProgressStatus = {100};
    int scorecounter = 0;
    int scorecountercolor = 0;
    int level = 0;
    Boolean mult2flag = false;
    int[] array = {1, 2, 3, 4, 5, 6};
    int[] array3 = {1, 2, 3};
    boolean ascending = true;
    int[] array9 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Boolean zerogamestar = false;
    int gamestar;

    private int rightBox;
    private int points;

    int iq;
    int tries = 0;
    Boolean iqplusone = false;
    Boolean vibration = true;
    final Random r = new Random();
    int NONE = 100;
    int ENGLISH = 0;
    int TURKISH = 1;
    boolean sifirla = false;
    int FRENCH = 2;
    int SPANISH = 3;
    int RUSSIAN = 4;
    int CHINESE = 5;
    int JAPANESE = 6;
    int PORTUGUESE = 7;
    Boolean notification = true;
    
    int gamelang = NONE;
    boolean mExplicitSignOut = false;
    boolean mInSignInFlow = false; // set to true when you're in the middle of the
    // sign in flow, to know you should not attempt
    // to connect in onStart()
    GoogleApiClient mGoogleApiClient;

    int REQUEST_LEADERBOARD = 10111;

    final int i1 = (r.nextInt(3) + 0);

    int colorarray[] = {Color.argb(255, 25, 189, 155), Color.argb(255, 255, 169, 221), Color.argb(255, 232, 75, 60), Color.argb(255, 53, 152, 219), Color.WHITE, Color.YELLOW, Color.argb(255, 143, 68, 173), Color.argb(255, 230, 125, 33)};
    final Activity cont = MainActivity.this;

    Boolean plus5gameflag = false;
    Boolean GoodlegO = false;
    Boolean facebookgo = false;
    Boolean videogo = false;
    Boolean instago = false;
    Boolean twittergo = false;
    Boolean facecoin = false;
    Boolean googlecoin = false;
    Boolean instacoin = false;
    Boolean twitcoin = false;
    boolean unlockiqflag = false;
    boolean adflag = false;

    Boolean backtooption = false;
    int adcount = 0;

    CountDownTimer waitTimer, waitTimer2, waitTimersort, waitTimercolordec, waitTimerpickcolor, waitTimermemory, waitTimerminesweeper, waitTimercircleshooter, waitTimercardcalculation, waitTimerpettrivia;


     static int ROW = 8;
     static int COL = 8;

    private int mines = 9;
    private boolean hardMode;

    int total;

    // int time = 0;
    int numflags = 0;

    Block block[][];
    TextView tgrid[][];

    boolean inUse[][];
    boolean isFlagged[][];
    int surrounding[][] = new int[100][100];
    int correct = 0;
    // int wrong = 0;
    // int score = 0;
    boolean isHappy = true;
    boolean gameOver = false;
    boolean isFlag = false;
    boolean isStarted = false;

    int count = 0;
    int width = 0;


    int globalt;

    private int heightgame = 0;
    private Drawable[][] drw = new Drawable[30][20];
    boolean buttonpressed = false;
    final Context x22 = this;
    private List<Drawable> images;
    int TURNTIME = 200;
    private TableLayout mainTable;
    private UpdateCardsHandler handler;
    private static Object lock = new Object();
    int correcttries = 0;
    int turns;
    private int[][] cards;
    final int[] gamescore = {1};
    private static int ROW_COUNT = -1;
    private static int COL_COUNT = -1;
    private Context context;
    private Drawable backImage;
    private Card seconedCard;
    private Card firstCard;
    private ButtonListener buttonListener;
    private ButtonListener2 buttonListener2;
    private int widthgame = 0;
    int wrongtries = 0;


    public final static int SOUND_WON = 0;
    public final static int SOUND_LOST = 1;
    public final static int SOUND_LAUNCH = 2;
    public final static int SOUND_DESTROY = 3;
    public final static int SOUND_REBOUND = 4;
    public final static int SOUND_STICK = 5;
    public final static int SOUND_HURRY = 6;
    public final static int SOUND_NEWROOT = 7;
    public final static int SOUND_NOH = 8;
    public final static int NUM_SOUNDS = 9;

    public final static int GAME_NORMAL = 0;
    public final static int GAME_COLORBLIND = 1;

    public final static int MENU_COLORBLIND_MODE_ON = 1;
    public final static int MENU_COLORBLIND_MODE_OFF = 2;
    public final static int MENU_FULLSCREEN_ON = 3;
    public final static int MENU_FULLSCREEN_OFF = 4;
    public final static int MENU_SOUND_ON = 5;
    public final static int MENU_SOUND_OFF = 6;
    public final static int MENU_DONT_RUSH_ME = 7;
    public final static int MENU_RUSH_ME = 8;
    public final static int MENU_NEW_GAME = 9;

    public final static int MENU_EDITOR = 11;
    public final static int MENU_TOUCHSCREEN_AIM_THEN_SHOOT = 12;
    public final static int MENU_TOUCHSCREEN_POINT_TO_SHOOT = 13;

    public final static String PREFS_NAME = "sushiShooter";
    private static Context ctx;
    private static int gameMode = GAME_NORMAL;
    private static boolean soundOn = true;
    private static boolean dontRushMe = false;
    private static boolean aimThenShoot = false;

    private boolean fullscreen = true;

    private GameView.GameThread mGameThread;
    private GameView mGameView;

    private static final String EDITORACTION = "quardy.ye.game.com.quardy.GAME";
    private boolean activityCustomStarted = false;

    Float BSCALE = 1f;
    Float BALPHA = 0.7f;
    private static int RC_SIGN_IN = 9001;

    boolean mResolvingConnectionFailure = false;
    boolean mAutoStartSignInflow = true;
    boolean mSignInClicked = false;

    final int MAX = 70;
    final int MIN = 0;

  /*  Integer[] mBitmapIds = new Integer[]{R.mipmap.file_22906akita300x189, R.mipmap.file_22908alaskanmalamute300x189, R.mipmap.file_22910anatolianshepherddog300x189, R.mipmap.file_22912bernesemountaindog300x189, R.mipmap.file_22914blackrussianterrier300x189, R.mipmap.file_22916boxer300x189, R.mipmap.file_22918_bullmastiff300x189, R.mipmap.file_22920_dobermanpinscher300x189, R.mipmap.file_22922_germanpinscher300x189, R.mipmap.file_22924_giantschnauzer300x189, R.mipmap.file_22926_greatdane300x189, R.mipmap.file_22928_greaterswissmountaindog300x189, R.mipmap.file_22930_komondor300x189, R.mipmap.file_22932_kuvasz300x189, R.mipmap.file_22934_mastiff300x189, R.mipmap.file_22936_neapolitanmastiff300x189, R.mipmap.file_22938_newfoundland300x189, R.mipmap.file_22940_portuguesewaterdog300x189, R.mipmap.file_22942_rottweiler300x189, R.mipmap.file_22944_saintbernard300x189, R.mipmap.file_22946_samoyeddogbreed300x189, R.mipmap.file_22948_siberianhusky300x189, R.mipmap.file_22950_standardschnauzer300x189, R.mipmap.file_22952_tibetanmastiff300x189, R.mipmap.file_22954_americanwaterspaniel300x189, R.mipmap.file_22956_brittany300x189, R.mipmap.file_22958_chesapeakebayretriever300x189,
            R.mipmap.file_22960_clumberspaniel300x189, R.mipmap.file_22962_cockerspaniel300x189, R.mipmap.file_22964_curlycoatedretriever300x189, R.mipmap.file_22966_englishcockerspaniel300x189, R.mipmap.file_22968_englishsetter300x189, R.mipmap.file_22970_englishspringerspaniel300x189, R.mipmap.file_22972_fieldspaniel300x189, R.mipmap.file_22974_flatcoatedretriever300x189, R.mipmap.file_22976_germanshorthairedpointer300x189, R.mipmap.file_22978_germanwirehairedpointer300x189, R.mipmap.file_22980_goldenretriever300x189, R.mipmap.file_22982_gordonsetter300x189, R.mipmap.file_22984_irishsetter300x189, R.mipmap.file_22986_irishwaterspaniel300x189, R.mipmap.file_22990_novascotiaducktollingretriever300x189, R.mipmap.file_22992_pointer300x189, R.mipmap.file_22994_sussexspaniel300x189, R.mipmap.file_22996_vizsla300x189, R.mipmap.file_22998_weimaraner300x189, R.mipmap.file_23000_welshspringerspaniel300x189, R.mipmap.file_23002_wirehairedpointinggriffon300x189, R.mipmap.file_23004_afghanhound300x189, R.mipmap.file_23006_americanfoxhound300x189, R.mipmap.file_23008_basenji300x189, R.mipmap.file_23010_bassethound300x189, R.mipmap.file_23012_beagle300x189, R.mipmap.file_23014_blackandtancoonhound300x189, R.mipmap.file_23016_bloodhound300x189,
            R.mipmap.file_23018_borzoi300x189, R.mipmap.file_23020_dachshunddogbreed300x189, R.mipmap.file_23022_englishfoxhound300x189, R.mipmap.file_23024_greyhound300x189, R.mipmap.file_23026_harrier300x189, R.mipmap.file_23028_ibizanhound300x189, R.mipmap.file_23030_irishwolfhound300x189, R.mipmap.file_23032_norwegianelkhound300x189, R.mipmap.file_23034_otterhound300x189, R.mipmap.file_23036_petitbassetgriffonvendeen300x189, R.mipmap.file_23038_pharaohhound300x189, R.mipmap.file_23040_plott300x189, R.mipmap.file_23042_rhodesianridgeback300x189, R.mipmap.file_23044_saluki300x189, R.mipmap.file_23046_scottishdeerhound300x189, R.mipmap.file_23048_whippet300x189, R.mipmap.file_23050_airedaleterrier300x189, R.mipmap.file_23052_pitbulldogbreedpittie300x189, R.mipmap.file_23054_australianterrier300x189, R.mipmap.file_23056_bedlingtonterrier300x189, R.mipmap.file_23058_borderterrier300x189, R.mipmap.file_23060_bullterrier300x189, R.mipmap.file_23062_cairnterrier300x189, R.mipmap.file_23064_dandiedinmontterrier300x189, R.mipmap.file_23066_glenofimaalterrier300x189, R.mipmap.file_23068_irishterrier300x189, R.mipmap.file_23074_miniatureschnauzer300x189, R.mipmap.file_23078norwichterrier300x189, R.mipmap.file_23080_jackrussellterrier300x189, R.mipmap.file_23084_sealyhamterrier300x189,
            R.mipmap.file_23086_skyeterrier300x189, R.mipmap.file_23088_softcoatedwheatenterrier300x189, R.mipmap.file_23090_welshterrier300x189, R.mipmap.file_23092_westhighlandwhiteterrier300x189, R.mipmap.file_23094_foxterrier300x189, R.mipmap.file_23096_affenpinscher300x189, R.mipmap.file_23098_brusselsgriffon300x189, R.mipmap.file_23100_cavalierkingcharlesspaniel300x189, R.mipmap.file_23102_chihuahua300x189, R.mipmap.file_23104_17524831300x189, R.mipmap.file_23106_englishtoyspaniel300x189, R.mipmap.file_23108_havanese300x189, R.mipmap.file_23110_italiangreyhound300x189, R.mipmap.file_23112_japanesechin300x189, R.mipmap.file_23116_miniaturepinscher300x189, R.mipmap.file_23118_papillon00x189, R.mipmap.file_23120_pekingese300x189, R.mipmap.file_23122_pomeranian300x189, R.mipmap.file_23124_pug300x189, R.mipmap.file_23126_shihtzu300x189, R.mipmap.file_23128_silkyterrier300x189, R.mipmap.file_23130_toyoxterrier300x189, R.mipmap.file_23132_yorkshireterrier300x189, R.mipmap.file_23134_americaneskimodog300x189, R.mipmap.file_23136_bichonfrise300x189, R.mipmap.file_23138_bostonterrier300x189, R.mipmap.file_23140_bulldog300x189, R.mipmap.file_23142_chinesesharpei300x189, R.mipmap.file_23144_chowchow300x189, R.mipmap.file_23146_dalmatian300x189,
            R.mipmap.file_23148_finnishspitz300x189, R.mipmap.file_23150_frenchbulldog300x189, R.mipmap.file_23152_keeshond300x189, R.mipmap.file_23158_schipperke300x189, R.mipmap.file_23160_shibainu300x189, R.mipmap.file_23162_tibetanspaniel300x189, R.mipmap.file_23164_tibetanterrier300x189, R.mipmap.file_23166_australiancattledog300x189, R.mipmap.file_23168_australianshepherd300x189, R.mipmap.file_23170_beardedcollie300x189, R.mipmap.file_23172_belgianmalinois300x189, R.mipmap.file_23174_belgiansheepdog300x189, R.mipmap.file_23176_belgianervuren300x189, R.mipmap.file_23178_bordercollie300x189, R.mipmap.file_23180_bouvierdesflandres300x189, R.mipmap.file_23182_briar300x189, R.mipmap.file_23184_canaandog300x189, R.mipmap.file_23186_collie300x189, R.mipmap.file_23188_germanshepherddog300x189, R.mipmap.file_23190_oldenglishsheepdog300x189, R.mipmap.file_23192_pembrokewelshcorgi300x189, R.mipmap.file_23194_polishlowlandsheepdog300x189, R.mipmap.file_23196_puli300x189, R.mipmap.file_23198_shetlansheepdog300x189, R.mipmap.file_23200_poodle300x189, R.mipmap.file_23202_greatpyrenees300x189, R.mipmap.file_23204_goldendoodle300x189, R.mipmap.file_23206_schnoodle300x189, R.mipmap.file_23208_yorkipoo300x189, R.mipmap.file_23210_maltipoo300x189, R.mipmap.file_23212_puggle300x189, R.mipmap.file_23214_goldador300x189,
            R.mipmap.file_23216_malteseshihtzu300x189, R.mipmap.file_23218_cockapoodogbreed300x189, R.mipmap.file_23220_whatisalabradoodle300x189, R.mipmap.file_23222_peekapoo300x189, R.mipmap.file_23224_manchesterterrier300x189, R.mipmap.file_23226_ratterrierdogbreed300x189, R.mipmap.file_23228_mutt300x189, R.mipmap.file_23230_cotondetulear300x189, R.mipmap.file_23232_chinook300x189, R.mipmap.file_23234_pocketbeagle300x189, R.mipmap.file_23236_americanenglishcoonhound300x189, R.mipmap.file_23238_bluetickcoonhound300x189, R.mipmap.file_23240_bolognese300x189, R.mipmap.file_23242_boykinspaniel300x189, R.mipmap.file_23244_whatistheappenzellersennenhundedog300x189, R.mipmap.file_23246_pyreneanshepherd300x189, R.mipmap.file_23248_redbonecoonhound300x189, R.mipmap.file_23250_treeingwalkercoonhound300x189, R.mipmap.file_23252_doguedebordeauxdogbreed300x189, R.mipmap.file_23254_irishredandwhitesetter300x189, R.mipmap.file_23256_smallmunsterlanderpointer300x189, R.mipmap.file_23258_finnishlapphund300x189, R.mipmap.file_23260_sloughidogbreedpicture300x189, R.mipmap.file_23262_entlebuchermountaindog300x189, R.mipmap.file_23264_stabyhoundogbreed300x189, R.mipmap.file_23266_kooikerhondje300x189, R.mipmap.file_23268_braccoitaliano300x189, R.mipmap.file_23270_ceskyterrierdogbreed300x189,
            R.mipmap.file_23274_icelandicsheepdog300x189, R.mipmap.file_23278_catahoulaleoparddog300x189, R.mipmap.file_23280_whatisatreeingtennesseebrindledog300x189, R.mipmap.file_23284_norwegianbuhund300x189, R.mipmap.file_23286_azawakh300x189, R.mipmap.file_23288_barbet300x189, R.mipmap.file_23290_bergerpicard300x189, R.mipmap.file_23292_staffordshirebullterrier300x189, R.mipmap.file_23294_cardiganwelshcorgi300x189, R.mipmap.file_23296_xoloitzcuintlidogbreed300x189, R.mipmap.file_23298_swedishvallhunddogbreed300x189, R.mipmap.file_23300_canecorsodogbreed300x189, R.mipmap.toyger, R.mipmap.turkishvan, R.mipmap.turkishangora, R.mipmap.tonkinese, R.mipmap.snowshoe, R.mipmap.savannah, R.mipmap.somali, R.mipmap.selkirkrex, R.mipmap.singapura, R.mipmap.scottishfold, R.mipmap.siamese, R.mipmap.sphynx, R.mipmap.ragamuffin, R.mipmap.russianblue, R.mipmap.ragdoll, R.mipmap.pixiebob, R.mipmap.persian, R.mipmap.ocicat, R.mipmap.orientalshorthair, R.mipmap.nebelung, R.mipmap.norwegianforest, R.mipmap.munchkincat, R.mipmap.manx, R.mipmap.mainecoon, R.mipmap.laperm, R.mipmap.korat, R.mipmap.japanesebobtail, R.mipmap.himalayancat, R.mipmap.havanabrown, R.mipmap.europeanburmese, R.mipmap.egyptianmau, R.mipmap.exoticshorthair, R.mipmap.devonrex, R.mipmap.cyprus, R.mipmap.cheetoh, R.mipmap.chausie, R.mipmap.chantilly, R.mipmap.cymric, R.mipmap.colorpointshorthair,
            R.mipmap.californiaspangled, R.mipmap.chartreux, R.mipmap.cornishrex, R.mipmap.britishsemilonghair, R.mipmap.brazilianshorthair, R.mipmap.bambinocat, R.mipmap.bengal, R.mipmap.burmilla, R.mipmap.bombaycat, R.mipmap.balinese, R.mipmap.burmese, R.mipmap.birman, R.mipmap.britishshorthair, R.mipmap.asiansemilonghair, R.mipmap.malayancat, R.mipmap.arabianmau, R.mipmap.americanpolydactyl, R.mipmap.australianmist, R.mipmap.aegean, R.mipmap.americanwirehair, R.mipmap.americabobtail, R.mipmap.americancurl1, R.mipmap.abyssinian_1, R.mipmap.americanshorthair, R.mipmap.abertstowhee,R.mipmap.acadianflycatcher,R.mipmap.acronwoodpecker,R.mipmap.alatmiraoriole,R.mipmap.alderflycatcher,R.mipmap.allenshummingbird,R.mipmap.americanbittern,R.mipmap.americanblackduck,R.mipmap.americancoot,R.mipmap.americandipper,R.mipmap.americangoldfinch,R.mipmap.americankestrel,R.mipmap.americanoystercatcher,R.mipmap.americanpipit,R.mipmap.americanrobin,R.mipmap.americantreesparwo,R.mipmap.americanwhitepelican,R.mipmap.americanwigeon,R.mipmap.ancientmurelet,R.mipmap.anhinga,R.mipmap.annasmockingbird,R.mipmap.arctictern,R.mipmap.arizonawoodpecker,R.mipmap.atlanticpuffin,R.mipmap.audobonsoriole,R.mipmap.bairdssandpiper,R.mipmap.baldeagle,R.mipmap.baltimoreoriole,R.mipmap.bandtailedpigeon,R.mipmap.bankswallow,
            R.mipmap.barnowl,R.mipmap.barnswallow,R.mipmap.barredowl,R.mipmap.barrowsgoldeneye,R.mipmap.baybreastedwarbler,R.mipmap.bellssparrow,R.mipmap.bellsvireo,R.mipmap.belted_kingfisher_glamor,R.mipmap.bendiresthrasher,R.mipmap.bewickswren,R.mipmap.black_and_white_warbler_glamor,R.mipmap.blackbackedwoodpecker,R.mipmap.blackbelliedplover,R.mipmap.blackbilledcuckoo,R.mipmap.blackbilledmagpie,R.mipmap.blackguillemot,R.mipmap.blackheadedgrosbeak,R.mipmap.blackheadedgull,R.mipmap.blackleggedkittiwake,R.mipmap.blackneckedstilt,R.mipmap.blackoystercatcher,R.mipmap.blackphoebe,R.mipmap.blackrail,R.mipmap.blackrosyfinch,R.mipmap.blackscoter,R.mipmap.blackskimmer,R.mipmap.blacktailedgnatcatcher,R.mipmap.blackthroatedgraywarbler,R.mipmap.blackturnstone,R.mipmap.blackvulture,R.mipmap.blackwhiskeredvireo,R.mipmap.bluefootedbooby,R.mipmap.bluejay,R.mipmap.bluethroatedhummingbird,R.mipmap.bluewingedwarbler,R.mipmap.boattailedgrackle,R.mipmap.boattailedoriole,R.mipmap.bobolink,R.mipmap.borealowl,R.mipmap.brewersblackbird,R.mipmap.bronzedcowbird, R.mipmap.browncappedrosyfinch,R.mipmap.brownheadedcowbird,R.mipmap.brownpelican,R.mipmap.buffbreastedflycatcher,R.mipmap.bullocksoriole,R.mipmap.cacklinggoose,R.mipmap.cactuswrenvyn,R.mipmap.california_quail_glamor,R.mipmap.californiagull_vyn,R.mipmap.californiathrasherlukeseitz,R.mipmap.californiatowhee,R.mipmap.canadagoose,
            R.mipmap.canyontowhee,R.mipmap.capemaywarbler

    };*/

    Integer[] mBitmapIds = {1};



    String[] question = {"Akita", "Alaskan Malamute", "Anatolian Sheperd Dog", "Bernese Mountain Dog", "Black Russian Terrier", "Boxer", "Bullmastiff", "Doberman Pinscher", "German Pinscher", "Giant Schanuzer", "Great Dane", "Greater Swiss Mountain Dog", "Komondor", "Kuvasz", "Mastiff", "Neapolitan Mastiff", "Newfound Land", "Portuguese Water Dog", "Rottweiler", "Saint Bernard", "Samoyed", "Siberian Husky", "Standard Schnauzer", "Tibetan Mastiff", "American Water Spaniel", "Brittany", "Chesapeake Bay Retriever", "Clumber Spaniel", "Cocker Spaniel", "Curly Coated Retriever", "English Cocker Spaniel", "English Setter", "English Springer Spaniel", "Field Spaniel", "Flat Coated Retriever", "German Shorthaired Pointer", "German Wirehaired Pointer", "Golden Retriever", "Gordon Setter", "Irish Setter", "Irish Water Spaniel", "Nova Scotia Duck Retriever", "Pointer", "Sussex Spaniel", "Vizsla", "Weimaraner", "Welsh Springer Spaniel", "Wire Haired Pointing Griffon", "Afghan Hound", "American Fox Hound", "Basenji", "Basset Hound", "Beagle", "Black and Tan Coonhound", "Bloodhound",
            "Borzoi", "Dachshund Dog Breed", "English Foxhound", "Greyhound", "Harrier", "Ibizan Hound", "Irish Wolfhound", "Norweigian Elkhound", "Otterhound", "Petit Basset Griffon Vendeen", "Pharaoh Hound", "Plott", "Rhodesian Ridgeback", "Saluki", "Scottish Deerhound", "Whippet", "Airedale Terrier", "Pitbull Dog Breed", "Australian Terrier", "Bedlington Terrier", "Border Terrier", "Bull Terrier", "Cairn terrier", "Dandiedin Montterrier", "Gleno Fimaaal Terrier", "Irish Terrier", "Miniature Schnauzer", "Norwich Terrier", "Jack Russell Terrier", "Sealyham Terrier", "Skye Terrier", "Soft Coated Wheaten Terrier", "Welsh Terrier", "Westhighland White Terrier", "Fox Terrier", "Affen Pinscher", "Brussels Griffon", "Cavalier King Charles Spaniel", "Chihuahua", "Chinese Crested", "English Toy Spaniel", "Havanese", "Italian Greyhound", "Japanese Chin", "Miniature Pinscher", "Papillon", "Peckingese", "Pomeranian", "Pug", "Shihtzu", "Silky Terrier", "Toyox Terrier", "Yorkshire Terrier", "American Eskimo Dog", "Bichonfrise", "Boston Terrier", "Bull Dog", "Chinese Sharpei", "Chow Chow", "Dalmatian", "Finnish Spitz", "French Bull Dog", "Keeshond", "Schipperke", "Shibainu", "Tibetans Spaniel",
            "Tibetian Terrier", "Australian Cattle Dog", "Australian Shepherd", "Bearded Collie", "Belgian Malinois", "Belgian Sheep Dog", "Belgian Ervuran", "Border Collie", "Bouvies des Flandres", "Briar", "Canaan Dog", "Collie", "German Shepherd Dog", "Old English Sheep Dog", "Pembroke Welsh Corgi", "Polishlowlands Sheep Dog", "Puli", "Shetlands Sheep Dog", "Poodle", "Great Pyrenees", "Golden Doodle", "Schnoole", "Yorkipoo", "Maltipoo", "Puggle", "Goldador", "Maltese Shitzu", "Cockapoo", "Labradoodle", "Peekapoo", "Manchester Terrier", "Rat Terrier", "Mutt", "Coton de Tulear", "Chinook", "Pocket Beagle", "American English Coon Hound", "Blue Tick Coon Hound", "Bolognese", "BoykinSpaniel", "Appenzeller Sennenhunde", "Pyreneanshephard", "Red Bone Coon Hound", "Treeing Walker Coonhound", "Dogue de Bordeaux", "Irish Red and White Setter", "Small Munsterlander Pointer", "Finnish Lapphund", "Sloughi", "Entlebucher Mountain Dog", "Stabby Hound", "Kooiker Hondje", "Bracco Italiano", "Cesky Terrier", "Icelendic Sheep Dog", " Catahoula Leopard Dog", "Treeing Tennesse Brindle", "Norweigian Buhund", "Azawakh", "Barbet", "Berger Picard", "Stafford Shire Bull Terrier", "Cardigan Welsh Corgi", "Xoloitzcuintli", "Sweedish Vallhund", "Cane Corso", "Toyger", "Turkishvan", "Tonkinese", "Snowshoe", "Savannah", "Somali",
            "Somali", "Selkirkrex", "Singapura", "Scottish Fold", "Siamese", "Sphynx", "Ragamuffin", "Russian Blue", "Ragdoll", "PixieBob", "Persian", "Ocicat", "Oriental Short Hair", "Nebelung", "Norwegian Forest Cat", "Munchkin Cat", "Manx", "Mainecoon", "Laperm", "Korat", "Japanese Bob Tail", "Himalayan Cat", "Havana Brown", "European Burmese", "Egyptian Mau", "Exotics Short Hair", "Devonrex", "Cyprus", "Cheetoh", "Chausie", "Chantilly", "Cymric", "Color Point Short Hair", "California Spanggled", "Chartreux", "Cornishrex", "British Semi Longhair", "Brazilian Short Hair", "Bambino", "Bengal", "Burmilia", "Bombay", "Balinese", "Burmese", "Birman", "British Short Hair", "Asian Semi Long Hair", "Malayan Cat", "Arabian Mau", "American Polydactyl", "Australian Mist", "Aegean", "American Wire Hair", "American Bob Tail", "American Cancurl", "Abyssinian", "American Short Hair",
            "Abert's Towhee","Acadian Fly Catcher","Acron Woodpecker","Alatmira Oriole","Alder Fly Catcher","Allen's Hummingbird","American Bittern","American Black Duck","American Coot","American Dipper","American Gold Finch" ,"American Kestrel","American Oyster Catcher","American Pipit","American Robin","American trees Parwo","American Whie Pelican","American Wigeon","Ancient Murelet","Anhinga","Annas Mockingbird","Arctic Tern","Arizona Woodpecker","Atlantic Puffin","Audobon's Oriole","Baird's Sandpiper","Bald Eagle","Baltimore Oriole","Band Tailed Pigeon","Bank Swallow","Barn Owl","Barn Swallow","Barred Owl","Barrow's Goldeneye","Bay Breasted Warbler","Bell's Sparow","Bell's Vireo","Belted Kingfisher","Bendire's Thrasher","Bewick's Wren","Black and White Warbler","Black Backed Woodpecker","Blackbellied Plover","Blackbilled Cuckoo","Blackbilled Magpie","Black guillemot","Black Headed Grosbeak","Black Headed Gull","Black Legged Kitti Wake","Black necked Stilt","Black Oyster Catcher","Black Phoebe","Black Rail","Black Rosfinch","Black Scoter","Black Skimmer","Black Tailed Gnat Cather","Black Throated Gray Warbler","Black Turn Stone","Black Vulture","Black Whiskered Vireo","Blue Footed Booby","Blue Jay","Blue Throated Humming Bird","Blue Winged Warbler",
            "Boat Tailed Grackle","Bat Tailed Oriole","Bobolink","Bobreal Owl","Brewer's Blackbird","Bronzed Cow Bird","Brown Caped Rosy Finch","Brown Headed Cow Bird","Brown pelican","Buff Breasted Fly Catcher","Bullock's Oriole","Cacling Goose","Cactus Wren", "Californian Quail","Californian Gull","Californian Trasher", "Californian Thewee","Canada Goose","Canyon Towhee","Capemay Warbler"
    };

    final int maxAD = 3;
    private static final String TAG = "Quardy debug:";


    public void heartanim()

    {
        ImageView heartanimimg = (ImageView) findViewById(R.id.heartanimlayout);
        ImageView heartanimimg2 = (ImageView) findViewById(R.id.heartanimlayout2);
        ImageView heartanimimg3 = (ImageView) findViewById(R.id.heartanimlayout3);

        AnimationDrawable rocketAnimation2;



        AnimationDrawable rocketAnimation;



        AnimationDrawable rocketAnimation3;





    if (mathlevel == 4)
    {
        heartanimimg.setBackgroundResource(R.drawable.heartanim);
        heartanimimg3.setBackgroundResource(R.drawable.heartanim3);
        heartanimimg2.setBackgroundResource(R.drawable.heartanim2);
    }


        if (mathlevel == 9)
        {
            heartanimimg.setBackgroundResource(R.drawable.h2eartanim);
            heartanimimg3.setBackgroundResource(R.drawable.h2eartanim3);
            heartanimimg2.setBackgroundResource(R.drawable.h2eartanim2);
        }

        else if (mathlevel == 14)
    {
        heartanimimg.setBackgroundResource(R.drawable.h3eartanim);
        heartanimimg3.setBackgroundResource(R.drawable.h3eartanim3);
        heartanimimg2.setBackgroundResource(R.drawable.h3eartanim2);
    }
        else if (mathlevel == 19 || mathlevel == 24)
        {
            heartanimimg.setBackgroundResource(R.drawable.h4eartanim);
            heartanimimg3.setBackgroundResource(R.drawable.h4eartanim3);
            heartanimimg2.setBackgroundResource(R.drawable.h4eartanim2);
        }

        else
        {
            heartanimimg.setBackgroundResource(R.drawable.h5eartanim);
            heartanimimg3.setBackgroundResource(R.drawable.h5eartanim3);
            heartanimimg2.setBackgroundResource(R.drawable.h5eartanim2);
        }




        rocketAnimation2 = (AnimationDrawable) heartanimimg.getBackground();
        rocketAnimation = (AnimationDrawable) heartanimimg2.getBackground();
        rocketAnimation3 = (AnimationDrawable) heartanimimg3.getBackground();

      if ( mathlevel == 4 ||  mathlevel == 9 ||  mathlevel == 14 ||  mathlevel == 19 ||  mathlevel == 24 ||  mathlevel == 29 ||  mathlevel == 34 )

      {
          heartanimimg.setVisibility(View.VISIBLE);

          rocketAnimation2.start();


          heartanimimg2.setVisibility(View.VISIBLE);

          rocketAnimation.start();

          heartanimimg3.setVisibility(View.VISIBLE);

          rocketAnimation3.start();
      }

        else {

          heartanimimg.setVisibility(View.INVISIBLE);

          rocketAnimation2.stop();


          heartanimimg2.setVisibility(View.INVISIBLE);

          rocketAnimation.stop();

          heartanimimg3.setVisibility(View.INVISIBLE);

          rocketAnimation3.stop();


      }

    }




    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed() called, result: " + connectionResult);

        if (mResolvingConnectionFailure) {
            Log.d(TAG, "onConnectionFailed() ignoring connection failure; already resolving.");
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, "signin other")) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences shared = getSharedPreferences("info", MODE_PRIVATE);

        //mExplicitSignOut = shared.getBoolean("mExplicitSignOut",mExplicitSignOut);


        if (!mInSignInFlow && !mExplicitSignOut) {
            // auto sign in
            mGoogleApiClient.connect();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //AppIndex.AppIndexApi.start(mGoogleApiClient, getIndexApiAction());
    }

    @Override
    protected void onStop() {

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        //AppIndex.AppIndexApi.end(mGoogleApiClient, getIndexApiAction());
        mGoogleApiClient.disconnect();

        long time2 = 0;

      /*  PublicServerTime x = new PublicServerTime();
        x.getNTPDate();
        if (x.getNTPDate() != null)
            time2 = x.getNTPDate().getTime();*/



        try{


            time2 =  getNetworkTime();
            // Log.d("yigit servertime:", "" + getNetworkTime());
        }

        catch(IOException e) {}



        time2 = time2 / 1000; // in minutes
        editor.putLong("time2", time2);
        editor.commit();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // The player is signed in. Hide the sign-in button and allow the
        // player to proceed.


        Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_id), mathlevel);
        findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.sign_in_button2).setVisibility(View.INVISIBLE);
        findViewById(R.id.button20).setVisibility(View.VISIBLE);
        findViewById(R.id.button9).setVisibility(View.VISIBLE);
    }
    Boolean onresume=false;
    @Override
    protected void onResume() {

        super.onResume();


        if (onresume && soundflag)

        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);

        }

    //  if(  !mediaPlayer.isPlaying() && mediaPlayer != null && soundflag) playMusic(this, R.raw.gamemusic) ;
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
      /*  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();

       // Log.d("gamecount", Integer.toString(gamecount));

        final SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);

        int mscore = shared1.getInt("mScore", 0);

        purchaseinfiniteflag = shared1.getBoolean("purchaseinfiniteflag", purchaseinfiniteflag);
        adcount = shared1.getInt("adcount", adcount);

        boolean backtomainfromjewel = shared1.getBoolean("backtomainfromjewel", false);

        if (backtomainfromjewel && adcount % maxAD == 0 && !adflag)

        {
            showad();

            editor.putBoolean("backtomainfromjewel", false);
            editor.putInt("gamecount", gamecount);
            editor.commit();

        }

        scorecounter = shared1.getInt("scorecounter", scorecounter);
        mathlevel = shared1.getInt("mathlevel", mathlevel);
        scorecounter += mscore;
        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);

        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }
        updatelevel();
        calculatedistance();

        updategamecount();
        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
    }

    Timer gametimer;




    boolean checkgamestar(int counter)

    {

        if (counter % gamewaittime == 0) {
            return true;


        }

        return false;

    }


    public void loadsignin() {

        final EditText e1 = (EditText) findViewById(R.id.editText);
        final EditText e2 = (EditText) findViewById(R.id.editText2);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                e1.setText(Name);
                e2.setText(FEmail);

            }

        }.start();


    }


    public void gametimerfunc() {
        final TextView newlifetimer = (TextView) findViewById(R.id.gametime);
        final SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);

        long tt1 = 0;
        /*PublicServerTime x = new PublicServerTime();
        x.getNTPDate();
        if(x.getNTPDate() != null)
            tt1  = x.getNTPDate().getTime();*/


        try{


            tt1 =  getNetworkTime();
            // Log.d("yigit servertime:", "" + getNetworkTime());
        }

        catch(IOException e) {}



        long timeservice2 = 0;
        SharedPreferences pref111;
        pref111 = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref111.edit();
        gamecount = shared1.getInt("gamecount", gamecount);
        timeservice2 = shared1.getLong("time2", timeservice2);

        long tt2 = tt1 / 1000;

        int gmstarcount = shared1.getInt("gamestarcounter", gamestarcounter);

        gamestarcounter = gmstarcount;
        long diff2 = gamestarcounter + (tt2 - timeservice2);
        if (diff2 < 0) {
            diff2 = diff2 * -1;

        }

        double gamdiv = (double) diff2 / (double) gamewaittime;
        double gamdiv2 = (double) diff2 % (double) gamewaittime;
        int div2 = (int) gamdiv;
        int div3 = (int) gamdiv2;
        gamestarcounter = div3;


        if ((gamecount + div2) <= MAXGAMEPLAY)
            gamecount = gamecount + div2;

        else gamecount = MAXGAMEPLAY;

        new CountDownTimer(900000000, 1000) {

            public void onTick(long millisUntilFinished) {

                gamestarcounter++;



                if (gamecount >= MAXGAMEPLAY) gamestarcounter = 0;


                int gamecountt = gamewaittime - (gamestarcounter % gamewaittime);

                if (gamecountt % 60 < 10)
                    newlifetimer.setText(Integer.toString(gamecountt / 60) + ":0" + Integer.toString(gamecountt % 60));
                else
                    newlifetimer.setText(Integer.toString(gamecountt / 60) + ":" + Integer.toString(gamecountt % 60));
                editor.putInt("gamestarcounter", gamestarcounter);


                editor.putBoolean("gamestop", false);
                gamelevelmanager();

                Long tt1 = 0l;
              /*  try{


                   tt1 =  getNetworkTime();
                   // Log.d("yigit servertime:", "" + getNetworkTime());
                }

                catch(IOException e) {}*/


               // long tt1 = 0;
                /*  PublicServerTime x = new PublicServerTime();
                x.getNTPDate();
                if(x.getNTPDate() != null)
                   // tt1  = x.getNTPDate().getTime();*/

               // Log.d("servertime",new Date(tt1) + " , " + new Date(System.currentTimeMillis()));

               // Log.d("phonetime",Long.toString(System.currentTimeMillis()));




              //  Log.d("gamestarcounter:", Integer.toString(gamestarcounter));
                editor.commit();

                if (checkgamestar(gamestarcounter))

                {
                    if (gamecount < MAXGAMEPLAY)
                        gamecount++;

                    else gamecount = MAXGAMEPLAY;


                    gamestarcounter = 0;
                    updategamecount();

                    editor.putInt("gamecount", gamecount);
                    editor.commit();

                }

            }

            public void onFinish() {

            }

        }.start();
    }

public void gettime()

{


}







    public void movehouses()

    {

        heartanim();
        Float density = Resources.getSystem().getDisplayMetrics().density;
        FrameLayout frame = (FrameLayout) findViewById(R.id.quardywalkinglayout);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams((int) (130 * density), (int) (130 * density));
        float distance = 80 * (scorecounter * 100 / LEVELMAX) / 100 + 25;





       Button house1 = (Button) findViewById(R.id.imageView6);
        Button house2 = (Button) findViewById(R.id.imageView7);
        Button house3 = (Button) findViewById(R.id.imageView8);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(house1.getLayoutParams());
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(house2.getLayoutParams());
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(house3.getLayoutParams());
        lp.setMargins(0, 0, 0, (int) (4*density));
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        house1.setLayoutParams(lp);

        double rx1= getWindowManager().getDefaultDisplay().getWidth();
        int rx = (int) (rx1/density);

        Log.d("mathlevel:",Integer.toString(mathlevel));
        if(mathlevel == 0) {
            house1.setVisibility(View.GONE);
            Log.d("house2.getx",Integer.toString((int)house2.getX()));




            Log.d("rx",Integer.toString(rx));


            distance = ((rx-200-10) * (scorecounter * 100 / LEVELMAX) / 100) - 15;


            lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lp3.setMargins(0,0, (int) (5*density), (int) (3*density));


            lp4.setMargins(0, 0, (int) (68*density), (int) (3*density));

            house3.setLayoutParams(lp3);
            house2.setLayoutParams(lp4);

        }
      else  if(mathlevel == 1){ house1.setVisibility(View.VISIBLE);



            distance = ((rx-230) * (scorecounter * 100 / LEVELMAX) / 100 )+ 17;
            lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lp3.setMargins(0,0, (int) (5*density), (int) (3*density));


            lp4.setMargins(0, 0, (int) (68*density), (int) (3*density));

            house3.setLayoutParams(lp3);
            house2.setLayoutParams(lp4);
        }
       else {
            house1.setVisibility(View.VISIBLE);

            lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lp4.setMargins( (int) (63*density),0, 0, (int) (3*density));

            lp4.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lp3.setMargins(0, 0, (int) (5*density), (int) (3*density));

            house3.setLayoutParams(lp3);
            house2.setLayoutParams(lp4);

            distance = ((rx-240) * (scorecounter * 100 / LEVELMAX) / 100) + 80;
        }


        Log.d("scorecounter", Integer.toString(scorecounter));


        lp2.setMargins((int) (density * distance), 0, 0, (int) (density * -1));
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        frame.setLayoutParams(lp2);

    }


    public void gamelevelmanager()

    {

        memorylevelmanager();
        sortinglevelmanager();
        secondmanager();
        colordeclevelmanager();
    }




    public void winfunc()

    {
        final RelativeLayout winlayout = (RelativeLayout) findViewById(R.id.winlayout);
        final Button awardtext = (Button) findViewById(R.id.awardheardimg);


        final RelativeLayout winlayout2 = (RelativeLayout) findViewById(R.id.awardlayout);
        final GifView fireworks = (GifView) findViewById(R.id.gifiqincrease);
        winlayout.setVisibility(View.VISIBLE);

        fireworks.speed = 1;
        fireworks.loadGIFResource(this, R.mipmap.fireworks);
        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.iqlevel);
        if(soundflag) {
            clickaudio2.start();
            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    clickaudio2.release();

                }

                ;
            });
        }

        final ImageView walkingbear2 = (ImageView) findViewById(R.id.imageView12);
        walkingbear2.setVisibility(View.VISIBLE);
        AnimationDrawable rocketAnimation2;
        walkingbear2.setBackgroundResource(R.drawable.winninganim);
        rocketAnimation2 = (AnimationDrawable) walkingbear2.getBackground();

        rocketAnimation2.start();

        if ( mathlevel == 5){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("1");

            if (gamecount<10) { gamecount++; updategamecount();  }

        }

        else  if ( mathlevel == 10){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("2");
            if (gamecount<9) { gamecount = gamecount + 2 ;updategamecount();  }
        }

        else  if ( mathlevel == 15){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("3");
            if (gamecount<8) { gamecount = gamecount + 3;updategamecount();  }
        }

        else  if ( mathlevel == 20){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("4");
            if (gamecount<7) { gamecount = gamecount + 4;updategamecount();  }
        }

        else  if ( mathlevel == 25){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("4");
            if (gamecount<7) { gamecount = gamecount + 4;updategamecount();  }
        }

        else  if ( mathlevel == 30){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("5");
            if (gamecount<6) { gamecount++;updategamecount();  }
        }

        else  if ( mathlevel == 35){

            winlayout2.setVisibility(View.VISIBLE);
            awardtext.setText("5");
            if (gamecount<6) { gamecount = gamecount + 5;updategamecount();  }
        }


    }


    public void showad() {


        if (mInterstitialAd.isLoaded())

        {
            mInterstitialAd.show();
        }
    }

    public void lifewarning()


    {

        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
        if(soundflag) {
            clickaudio2.start();
            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    clickaudio2.release();

                }

                ;
            });
        }
        final RelativeLayout nonelayout = (RelativeLayout) findViewById(R.id.nonelayout);

        final RelativeLayout lifewarninglayout = (RelativeLayout) findViewById(R.id.gamecountwarning);

        lifewarninglayout.setVisibility(View.VISIBLE);
        nonelayout.setVisibility(View.VISIBLE);

        nonelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        lifewarninglayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void onClickRequestButton() {

        GameRequestContent content = new GameRequestContent.Builder()
                .setMessage("Come play this level with me")
                .build();
        requestDialog.show(content);


      /*  GameRequestContent content = new GameRequestContent.Builder()
                .setMessage("Come play this level with me")
                .setTo("USER_ID")
                .setActionType(GameRequestContent.ActionType.SEND)
                .setObjectId("YOUR_OBJECT_ID")
                .build();
        requestDialog.show(content);*/
    }

    void startalarm()

    {

        Calendar cal = Calendar.getInstance();
        /*Intent intent = new Intent(this, MyService.class);
        PendingIntent pintent = PendingIntent
                .getBroadcast(this, 10, intent, 0);*/

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Start service every 20 seconds

        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                10 * 1000, pintent);



    }



    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("gamestop", true);
        editor.commit();

        super.onDestroy();

        long time2 = 0;

      /*  PublicServerTime x = new PublicServerTime();
        x.getNTPDate();
        if(x.getNTPDate() != null)
            time2  = x.getNTPDate().getTime();*/


        try{


            time2 =  getNetworkTime();
            // Log.d("yigit servertime:", "" + getNetworkTime());
        }

        catch(IOException e) {}


        time2 = time2 / 1000; // in minutes
        editor.putLong("time2", time2);
        editor.commit();
    }

    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 4;
    CallbackManager callbackManager;
    LoginButton loginButton, loginButton2;
    GameRequestDialog requestDialog;
    String Name;
    String FEmail;
    String Birthday;
    String gender;
    AdRequest adRequest;
    BillingProcessor bp;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);

        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                mGoogleApiClient.connect();
            } else {
                // Bring up an error dialog to alert the user that sign-in
                // failed. The R.string.signin_failure should reference an error
                // string in your strings.xml file that tells the user they
                // could not be signed in, such as "Unable to sign in."
                BaseGameUtils.showActivityResultError(this,
                        requestCode, resultCode, R.string.signin_failure);
            }
        }

    }

    private void requestNewInterstitial() {
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("7C835159CEFFDA7A41942B2401E33E40")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void shopbuttonupdate() {

        final Button buy2life = (Button) findViewById(R.id.buylife1);
        final Button buy4life = (Button) findViewById(R.id.buylife2);
        final Button buy6life = (Button) findViewById(R.id.buylife3);
        final Button buyinfinitelife = (Button) findViewById(R.id.buylife4);

        final boolean isAvailable = BillingProcessor.isIabServiceAvailable(this);


        if (bp.isInitialized() && isAvailable) {
            SkuDetails skun = bp.getPurchaseListingDetails("life2");
            SkuDetails skun2 = bp.getPurchaseListingDetails("life4");
            SkuDetails skun3 = bp.getPurchaseListingDetails("life6");
            SkuDetails skun4 = bp.getPurchaseListingDetails("lifeinfinite");

            if (skun != null && skun2 != null && skun3 != null &&skun4 != null) {
                buy2life.setText(Double.toString(round(skun.priceValue, 2)) + " " + skun.currency);
                buy4life.setText(Double.toString(round(skun2.priceValue, 2)) + " " + skun2.currency);
                buy6life.setText(Double.toString(round(skun3.priceValue, 2)) + " " + skun3.currency);
                buyinfinitelife.setText(Double.toString(round(skun4.priceValue, 2)) + " " + skun4.currency);
            }

        }


        if (gamecount > 8) {
            buy2life.setAlpha(0.5f);
            buy2life.setEnabled(false);
            buy4life.setAlpha(0.5f);
            buy4life.setEnabled(false);
            buy6life.setAlpha(0.5f);
            buy6life.setEnabled(false);
        } else if (gamecount > 6) {
            buy2life.setAlpha(1f);
            buy2life.setEnabled(true);
            buy4life.setAlpha(0.5f);
            buy4life.setEnabled(false);
            buy6life.setAlpha(0.5f);
            buy6life.setEnabled(false);
        } else if (gamecount > 4) {
            buy2life.setAlpha(1f);
            buy2life.setEnabled(true);
            buy4life.setAlpha(1f);
            buy4life.setEnabled(true);
            buy6life.setAlpha(0.5f);
            buy6life.setEnabled(false);
        } else {
            buy2life.setAlpha(1f);
            buy2life.setEnabled(true);
            buy4life.setAlpha(1f);
            buy4life.setEnabled(true);
            buy6life.setAlpha(1f);
            buy6life.setEnabled(true);
        }


        if (purchaseinfiniteflag) {
            buyinfinitelife.setAlpha(0.5f);
            buyinfinitelife.setEnabled(false);
            buy2life.setAlpha(0.5f);
            buy2life.setEnabled(false);
            buy4life.setAlpha(0.5f);
            buy4life.setEnabled(false);
            buy6life.setAlpha(0.5f);
            buy6life.setEnabled(false);

        } else {


            buyinfinitelife.setAlpha(1f);
            buyinfinitelife.setEnabled(true);
        }


    }


int GAMEPLAYTIME2 = 24000;
    public void secondmanager()
    {
        if (mathlevel<=5) {
            GAMEPLAYTIME = 50500;
            GAMEPLAYTIME2 = 50000;
            LEVELMAX = 20000;
        }

        if (mathlevel<=10 && mathlevel>5) {
            GAMEPLAYTIME = 45500;
            GAMEPLAYTIME2 = 45000;
            LEVELMAX = 22000;
        }

        if (mathlevel<=15 && mathlevel>10) {
            GAMEPLAYTIME = 40500;
            GAMEPLAYTIME2 = 40000;
            LEVELMAX = 24000;
        }

        if (mathlevel<=20 && mathlevel>15) {
            GAMEPLAYTIME = 35500;
            GAMEPLAYTIME2 = 35000;
            LEVELMAX = 26000;
        }

        if (mathlevel<=20 && mathlevel>30) {
            GAMEPLAYTIME = 32500;
            GAMEPLAYTIME2 = 32000;
            LEVELMAX = 27000;
        }

        if (mathlevel >30 ) {
            GAMEPLAYTIME = 30500;
            GAMEPLAYTIME2 = 30000;
            LEVELMAX =30000;
        }


    }




    void cancelalarm()

    {


        //Intent intent = new Intent(this, MyService.class);
        //PendingIntent pintent = PendingIntent
        //      .getBroadcast(this, 10, intent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Start service every 20 seconds

        alarm.cancel(pintent);

    }


    int login2 = 0;

    int alarmId = 11;
    Boolean soundflag = true;
    Intent intent;
    PendingIntent pintent;

    private static MediaPlayer mediaPlayer;

    @Override
    protected void onPause() {


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object
        final SharedPreferences.Editor editor = pref.edit();
        super.onPause();
       if (mediaPlayer != null)  mediaPlayer.pause();


        long time2 = 0;

       /* PublicServerTime x = new PublicServerTime();
        x.getNTPDate();
        if(x.getNTPDate() != null)
            time2  = x.getNTPDate().getTime();*/




        try{


            time2 =  getNetworkTime();
            // Log.d("yigit servertime:", "" + getNetworkTime());
        }

        catch(IOException e) {}



        time2 = time2 / 1000; // in minutes
        editor.putLong("time2", time2);
        editor.commit();
    }


    public static void playMusic(Context con, int id)
    {
        mediaPlayer = MediaPlayer.create(con, id);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
       /* getWindow().getDecorView().setSystemUiVisibility(
                //        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);*/


        MobileAds.initialize(this, "ca-app-pub-6979765215028091~1634927761");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_main);

        intro();
        intent = new Intent(this, myService.class);
        pintent = PendingIntent
                .getBroadcast(this, 10, intent, 0);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        cancelalarm();
        //// Dynamically assign alarm ids for multiple alarms
        Intent service = new Intent(this, myService.class);  //your Intent localIntent = new Intent("com.test.sample");
        //intent.putExtra("alarmId", alarmId); // So we can catch the id on BroadcastReceiver
        PendingIntent alarmIntent;
        alarmIntent = PendingIntent.getBroadcast(this,
                alarmId, service,
                0);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        am.cancel(alarmIntent);
        Intent intentnew = new Intent(this, myService.class);

        intentnew.setAction("STOP");
        SharedPreferences pref1;
        pref1 = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref1.edit();
        String licencekey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAibupmhwU4aOVHFUd4nZ1kLgr7Ka+/awknhR94LWm7rU64f7x6IqRDZ25igCoHjfWkYIkUYuuelirS6tOWBgm7B8KshaxrH4e55atFNyGe/GRPl0xVORlYZ+0htg9B1syczYofKqf/S0HUylg4lLRDzjEyAgKX407btoydK1BHMUSm3d2vij5Cxo5KezMuKK0rn/QIvzDre28k5OxQOA4Ol4AuR0LTn3R0wAW802FW2HDAwi9ULueSDItlOD6xdZyA5UzBeRy2jGAX6xHubXP3w9OhrIRXInaSpSnBFfVxQOsrka2gEfBPFpE9RysEvXAoqlfcciNKaabHmL8bklOXQIDAQAB";
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        bp = new BillingProcessor(this, licencekey, this);
        AdView mAdView = (AdView) findViewById(R.id.adView);

        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
         //       .addTestDevice("7C835159CEFFDA7A41942B2401E33E40")

                .build();

        final SharedPreferences shared1 = getSharedPreferences("info", MODE_PRIVATE);
        login2 = shared1.getInt("login", 0);
        soundflag = shared1.getBoolean("soundflag", soundflag);
        //AdRequest.Builder.addTestDevice();
        mAdView.loadAd(adRequest);
        shopbuttonupdate();


        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volume = actVolume / maxVolume;

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {


            }

        });


        soundPool2 = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundPool2.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

            @Override

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {


            }

        });
        onresume = shared1.getBoolean("onresume", onresume);
        soundID = soundPool.load(this, R.raw.click2017, 1);
        soundID2 = soundPool2.load(this, R.raw.hip_hop, 1);


        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();


        if (soundflag) playMusic(this, R.raw.gamemusic);

        onresume = true;

        final LinearLayout insertdatavew = (LinearLayout) findViewById(R.id.login1);
        final LinearLayout insertdatavew2 = (LinearLayout) findViewById(R.id.login2);

        final RelativeLayout optionlayout = (RelativeLayout) findViewById(R.id.optionslayout);
        final Button backbutton = (Button) findViewById(R.id.buttonexit);
        final Button optionsbutton = (Button) findViewById(R.id.buttonoptions);
        final RelativeLayout nonelayout = (RelativeLayout) findViewById(R.id.nonelayout);

        final LinearLayout insertdatavew3 = (LinearLayout) findViewById(R.id.login3);

        final RelativeLayout signinlayout = (RelativeLayout) findViewById(R.id.signinlayout);

        secondmanager();
        memorylevelmanager();
        optionlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        nonelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        //  if(!mGoogleApiClient.isConnected())   mGoogleApiClient.connect();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6979765215028091/7450691761");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

            }
        });


        mInterstitialAd.loadAd(adRequest);

        startService(new Intent(this, myService.class));
        startalarm();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton2 = (LoginButton) findViewById(R.id.login_button2);

        // final SignInButton googlesignin = (SignInButton) findViewById(R.id.sign_in_button);
        final Button googlesignin = (Button) findViewById(R.id.sign_in_button2);

        final Button leaderboardgoogle = (Button) findViewById(R.id.button9);

        final Button buy2life = (Button) findViewById(R.id.buylife1);
        final Button buy4life = (Button) findViewById(R.id.buylife2);
        final Button buy6life = (Button) findViewById(R.id.buylife3);
        final Button buyinfinitelife = (Button) findViewById(R.id.buylife4);

        final boolean isAvailable = BillingProcessor.isIabServiceAvailable(this);

        final RelativeLayout newgamelayout = (RelativeLayout) findViewById(R.id.newgamepopup);

        final RelativeLayout nonelayout2 = (RelativeLayout) findViewById(R.id.nonelayout2);
        final Button yesbutton = (Button) findViewById(R.id.yesbutton);
        final Button nobutton = (Button) findViewById(R.id.nobutton);
        final Button newgamebutton = (Button) findViewById(R.id.button5);


        final RelativeLayout awardlayout = (RelativeLayout) findViewById(R.id.awardlayout);
        final Button awardexit = (Button) findViewById(R.id.awardexit);


        awardexit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        awardexit.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        awardlayout.setVisibility(View.INVISIBLE);


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        awardexit.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        awardlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        nonelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        nonelayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        yesbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        yesbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        newgamelayout.setVisibility(View.INVISIBLE);
                        nonelayout2.setVisibility(View.INVISIBLE);
                        optionlayout.setVisibility(View.INVISIBLE);
                        nonelayout.setVisibility(View.INVISIBLE);
                        mathlevel = 0;
                        scorecounter = 0;
                        gamecount = 10;
                        updategamecount();
                        updatelevel();


                        login2 = 0;

                        editor.putInt("login", login2);
                        editor.putInt("scorecounter", scorecounter);
                        editor.putInt("gamecount", gamecount);
                        editor.putInt("mathlevel", mathlevel);

                        editor.commit();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        yesbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        nobutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        nobutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        newgamelayout.setVisibility(View.INVISIBLE);
                        nonelayout2.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        nobutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        newgamebutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        newgamebutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        nonelayout2.setVisibility(View.VISIBLE);
                        newgamelayout.setVisibility(View.VISIBLE);
                        final MediaPlayer clickaudio2 = MediaPlayer.create(getApplicationContext(), R.raw.popupsound);
                        if (soundflag) {
                            clickaudio2.start();
                            clickaudio2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    clickaudio2.release();

                                }

                                ;
                            });
                        }
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        newgamebutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        final Button signoutgoogle = (Button) findViewById(R.id.button20);

        signoutgoogle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        signoutgoogle.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        mSignInClicked = false;
                        mExplicitSignOut = true;

                        editor.putBoolean("mExplicitSignOut", mExplicitSignOut);

                        editor.commit();
                        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                            Games.signOut(mGoogleApiClient);
                            mGoogleApiClient.disconnect();
                        }


                        // show sign-in button, hide the sign-out button
                        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
                        findViewById(R.id.sign_in_button2).setVisibility(View.VISIBLE);
                        findViewById(R.id.button20).setVisibility(View.GONE);
                        findViewById(R.id.button9).setVisibility(View.GONE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        signoutgoogle.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        buy2life.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buy2life.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            purchaseint = PURCHASE2;
                            bp.purchase(MainActivity.this, purchaselife2);

                            // bp.getPurchaseTransactionDetails("coins");
                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buy2life.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        buy4life.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buy4life.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            purchaseint = PURCHASE4;
                            bp.purchase(MainActivity.this, purchaselife4);

                            // bp.getPurchaseTransactionDetails("coins");
                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buy4life.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        buy6life.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buy6life.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            purchaseint = PURCHASE6;
                            bp.purchase(MainActivity.this, purchaselife6);

                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buy6life.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        buyinfinitelife.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buyinfinitelife.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (isAvailable) {
                            purchaseint = PURCHASEinf;
                            bp.purchase(MainActivity.this, purchaselifeinf);

                            // bp.getPurchaseTransactionDetails("coins");
                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buyinfinitelife.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        leaderboardgoogle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        leaderboardgoogle.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_id), mathlevel);

                        startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                                getString(R.string.leaderboard_id)), REQUEST_LEADERBOARD);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        leaderboardgoogle.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        googlesignin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        googlesignin.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        mSignInClicked = true;

                        if (!mGoogleApiClient.isConnected())

                        {
                            Log.d("yigit: ", "trying to connect");
                            mGoogleApiClient.connect();
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        googlesignin.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    /*    new CountDownTimer(7000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                if (!mGoogleApiClient.isConnected())

                    mGoogleApiClient.connect();
            }

        }.start(); */


        //   loginButton.setReadPermissions("email");
        loginButton.setReadPermissions(Arrays.asList(
                "email", "user_birthday", "user_friends"));

        callbackManager = CallbackManager.Factory.create();


        requestDialog = new GameRequestDialog(this);
        requestDialog.registerCallback(callbackManager,
                new FacebookCallback<GameRequestDialog.Result>() {
                    public void onSuccess(GameRequestDialog.Result result) {
                        // String id = result.getId();
                    }

                    public void onCancel() {
                    }

                    public void onError(FacebookException error) {
                    }
                }
        );


        loginButton2.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "quardy.ye.game.com.quard",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        Button bb7 = (Button) findViewById(R.id.button7);


        bb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                String text = "https://play.google.com/store/apps/details?id=quardy.ye.game.com.quardy";

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, text);

                shareIntent.setType("text/plain");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.sharequardy)));
                //onClickRequestButton();
            }
        });


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        Log.v("LoginActivity Response ", response.toString());

                                        try {
                                            Name = object.getString("name");

                                            FEmail = object.getString("email");
                                            Birthday = object.getString("birthday");
                                            gender = object.getString("gender");

                                            Log.v("Birthday = ", " " + Birthday);
                                            Log.v("gender = ", " " + gender);
                                            Log.v("Email = ", " " + FEmail);

                                            Log.v("name = ", " " + Name);

                                            Toast.makeText(getApplicationContext(), "Name " + Name, Toast.LENGTH_LONG).show();


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                        loadsignin();
                        insertdatavew.setVisibility(View.INVISIBLE);
                        insertdatavew2.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


        String AppID = "1778578115804664";

    /*    new GraphRequest(AccessToken.getCurrentAccessToken(), "/" + AppID + "/scores", null, HttpMethod.GET, new GraphRequest.Callback() {

            public void onCompleted(GraphResponse response) {

                JSONObject jsonObj = response.getJSONObject();

             try {
                 JSONArray dataArray = jsonObj.getJSONArray("data");

                 for (int i=0; i< dataArray.length(); i++) {

                     //Log.d("json",dataArray[0]);

                     //do something
                 }

             }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }).executeAsync();*/


        final RelativeLayout quardyshoplayout = (RelativeLayout) findViewById(R.id.quardyshop);
        final Button shopexit = (Button) findViewById(R.id.shopexit);
        final Button shopbutton = (Button) findViewById(R.id.textView24);
        gametimerfunc();

        quardyshoplayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        final Button exitwarning = (Button) findViewById(R.id.button14);

        final Button gotoshop = (Button) findViewById(R.id.button4);
        final RelativeLayout lifewarninglayout = (RelativeLayout) findViewById(R.id.gamecountwarning);


        final RelativeLayout leaderlayout = (RelativeLayout) findViewById(R.id.leaderboard);
        final Button leadbutton = (Button) findViewById(R.id.button6);
        final Button leadexit = (Button) findViewById(R.id.buttonexit2);


        final Button backfromwinlayout = (Button) findViewById(R.id.buttonexit3);
        final RelativeLayout winlayout = (RelativeLayout) findViewById(R.id.winlayout);

        backfromwinlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backfromwinlayout.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        winlayout.setVisibility(View.INVISIBLE);
                        nonelayout.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backfromwinlayout.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        leadbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        leadbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        leaderlayout.setVisibility(View.VISIBLE);
                        nonelayout.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        leadbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        leadexit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        leadexit.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        leaderlayout.setVisibility(View.INVISIBLE);
                        nonelayout.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        leadexit.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        exitwarning.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        exitwarning.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        lifewarninglayout.setVisibility(View.INVISIBLE);
                        nonelayout.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        exitwarning.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        gotoshop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        gotoshop.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        quardyshoplayout.setVisibility(View.VISIBLE);
                        lifewarninglayout.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        gotoshop.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        shopbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        shopbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        quardyshoplayout.setVisibility(View.VISIBLE);
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        shopbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        shopexit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        shopexit.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        quardyshoplayout.setVisibility(View.INVISIBLE);


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        shopexit.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        optionsbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        optionsbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        optionlayout.setVisibility(View.VISIBLE);
                        nonelayout.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        optionsbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        backbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        nonelayout.setVisibility(View.INVISIBLE);
                        optionlayout.setVisibility(View.INVISIBLE);
                        STAGEE = MENUSTAGE;
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        final ImageView walkingbear = (ImageView) findViewById(R.id.imageView5);
        final Button Leveltext = (Button) findViewById(R.id.textView22);
        final TextView lifetext = (TextView) findViewById(R.id.textView23);


        final TextView pathtext = (TextView) findViewById(R.id.textView28);


        AnimationDrawable rocketAnimation;
        walkingbear.setBackgroundResource(R.drawable.walkinganim);
        rocketAnimation = (AnimationDrawable) walkingbear.getBackground();

        rocketAnimation.start();


        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/GoodDog.otf");

        //Leveltext.setTypeface(tf);
        //lifetext.setTypeface(tf);

        //Leveltext.setTextSize(16f);
        // progresstext.setTextSize(16f);
        //progresstext.setTypeface(tf);
        pathtext.setTypeface(tf);


        final Button bb1 = (Button) findViewById(R.id.button1);
        final Button bb2 = (Button) findViewById(R.id.button2);
        final Button bb3 = (Button) findViewById(R.id.button3);


        final Button operationbtn = (Button) findViewById(R.id.button12);

        final RelativeLayout mathcategory = (RelativeLayout) findViewById(R.id.Mathcategory);

        final RelativeLayout colorcategory = (RelativeLayout) findViewById(R.id.colorcategory);
        final RelativeLayout shootercategroy = (RelativeLayout) findViewById(R.id.shooterlayout);


        final Button login = (Button) findViewById(R.id.button8);


        final Button buttonnext = (Button) findViewById(R.id.button10);


        final TextView textname = (TextView) findViewById(R.id.textView6);
        final EditText playerid = (EditText) findViewById(R.id.editText);
        final EditText emailgettext = (EditText) findViewById(R.id.editText2);


        gamecount = shared1.getInt("gamecount", gamecount);
        highest = shared1.getInt("highestmath", highest);
        highestcolor = shared1.getInt("highestcolor", highestcolor);

        scorecounter = shared1.getInt("scorecounter", scorecounter);
        mathlevel = shared1.getInt("mathlevel", mathlevel);


        updatelevel();

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        calculatedistance();


        adcount = shared1.getInt("adcount", adcount);
        updategamecount();
        //final TextView tname = (TextView) findViewById(R.id.textView12);
    /*   if (login2 == 1)


        {
            signinlayout.setVisibility(View.VISIBLE);
            insertdatavew.setVisibility(View.INVISIBLE);
            insertdatavew2.setVisibility(View.INVISIBLE);
            insertdatavew3.setVisibility(View.VISIBLE);

            String text = shared1.getString("playerid", "");
            textname.setText(text);
            // tname.setText(text);

            new CountDownTimer(2000, 100) {
                public void onTick(long milsec) {
                }

                public void onFinish() {

                    signinlayout.setVisibility(View.INVISIBLE);
                    activatecategories();

                }
            }.start();


        }*/
//Using putXXX - with XXX is type data you want to write like: putString, putInt...   from      Editor object


        final RelativeLayout operationlayout = (RelativeLayout) findViewById(R.id.opeartionlayout);
        final RelativeLayout simplicitylayout = (RelativeLayout) findViewById(R.id.simpicitylayout);
        final RelativeLayout sortinglayout = (RelativeLayout) findViewById(R.id.sortingrelative);
        final RelativeLayout coloerdeclayout = (RelativeLayout) findViewById(R.id.colordeceptionlayout);
        final RelativeLayout pickcolorlayout = (RelativeLayout) findViewById(R.id.pickcolorlayout);
        final RelativeLayout memeorylayout = (RelativeLayout) findViewById(R.id.memorylayout);
        final RelativeLayout minesweeperlayout = (RelativeLayout) findViewById(R.id.minesweeperlayout);
        final RelativeLayout circleshooterlayout = (RelativeLayout) findViewById(R.id.colorshooter);


        newgamelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        circleshooterlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        minesweeperlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        coloerdeclayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        pickcolorlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        memeorylayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        operationlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        simplicitylayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        sortinglayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        final Button simplicitybutton = (Button) findViewById(R.id.button15);
        final Button sortbutton = (Button) findViewById(R.id.buttonsorti);


        final Button colordecbutton = (Button) findViewById(R.id.buttonpettrvia);
        final Button pickcolorbutton = (Button) findViewById(R.id.buttonpickcolor);


        final Button memeorybutton = (Button) findViewById(R.id.buttonmemory);
        final Button minesweeper = (Button) findViewById(R.id.button16minesweeper);
        final Button colorshooterbut = (Button) findViewById(R.id.buttonshootercol);

        final Button crushbutton = (Button) findViewById(R.id.buttonshootercol2);

        final Button cardmathcalc = (Button) findViewById(R.id.buttoncardmem);

        final RelativeLayout mathcalrelative = (RelativeLayout) findViewById(R.id.cardcalculation);

        final Button pettrivia = (Button) findViewById(R.id.buttonpettrvia);
        final RelativeLayout pettrivialayout = (RelativeLayout) findViewById(R.id.pettrivialayout);

        final Button soundonoff = (Button) findViewById(R.id.button13);


        if (!soundflag) {

            soundonoff.setBackgroundResource(R.drawable.buttonroundred);
            soundonoff.setText(getResources().getString(R.string.soundoff));


        } else {

            soundonoff.setText(getResources().getString(R.string.soundon));
            soundonoff.setBackgroundResource(R.drawable.bordergreen);
        }


        soundonoff.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        soundonoff.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) {
                            soundPool.play(soundID, volume, volume, 1, 0, 1f);
                            soundonoff.setBackgroundResource(R.drawable.buttonroundred);
                            soundflag = false;
                            soundonoff.setText(getResources().getString(R.string.soundoff));
                            mediaPlayer.pause();
                            editor.putBoolean("soundflag", soundflag);
                            editor.commit();
                        } else {
                            soundonoff.setText(getResources().getString(R.string.soundon));
                            soundonoff.setBackgroundResource(R.drawable.bordergreen);
                            playMusic(MainActivity.this, R.raw.gamemusic);
                            soundflag = true;
                            editor.putBoolean("soundflag", soundflag);
                            editor.commit();

                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        soundonoff.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


       /* pettrivia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        pettrivia.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        // insertdatavew.setVisibility(View.INVISIBLE);
                            STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag)

                        {
                            pettrivialayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        pettrivia.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });*/


        crushbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        crushbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag) {
                            gamecount--;


                            editor.putInt("gamecount", gamecount);
                            editor.commit();

                            updategamecount();
                            Intent intent = new Intent();
                            intent.putExtra("mode", "normal");
                            intent.setClass(getApplicationContext(), Jewels.class);
                            startActivity(intent);
                        } else lifewarning();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        crushbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        mathcalrelative.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        cardmathcalc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        cardmathcalc.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // insertdatavew.setVisibility(View.INVISIBLE);
                        if (gamecount > 0) {
                            mathcalrelative.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();
                        STAGEE = GAMESTAGE;
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        cardmathcalc.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        colorshooterbut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        colorshooterbut.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag) {
                            circleshooterlayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();
                        STAGEE = GAMESTAGE;
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        colorshooterbut.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        minesweeper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        minesweeper.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag) {
                            minesweeperlayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        minesweeper.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        memeorybutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        memeorybutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag) {
                            memeorylayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        memeorybutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        pickcolorbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        pickcolorbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        if (gamecount > 0) {
                            pickcolorlayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        pickcolorbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        colordecbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        colordecbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0) {
                            coloerdeclayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        colordecbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        sortbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        sortbutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (gamecount > 0 || purchaseinfiniteflag) {
                            sortinglayout.setVisibility(View.VISIBLE);
                            updateScoresort();
                        } else lifewarning();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        sortbutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        simplicitybutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        simplicitybutton.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        STAGEE = GAMESTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // insertdatavew.setVisibility(View.INVISIBLE);

                        simplicitylayout.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        simplicitybutton.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        operationbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        operationbtn.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        // insertdatavew.setVisibility(View.INVISIBLE);

                        operationlayout.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        operationbtn.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        buttonnext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        buttonnext.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        // insertdatavew.setVisibility(View.INVISIBLE);
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        String s = playerid.getText().toString();
                        String s1 = emailgettext.getText().toString();
                        if (s.isEmpty()) alertbox();

                            // else if (!isEmailValid(s1)) { alertboxemail();}

                        else {

                            insertdatavew2.setVisibility(View.INVISIBLE);

                            insertdatavew3.setVisibility(View.VISIBLE);
                            Log.d("yigitvele", s);
                            editor.putString("playerid", s);
                            editor.putString("email", s1);
                            editor.putInt("login", 1);

                            editor.commit();


                            textname.setText(playerid.getText().toString());


                            new CountDownTimer(2000, 100) {
                                public void onTick(long milsec) {
                                }

                                public void onFinish() {

                                    signinlayout.setVisibility(View.INVISIBLE);
                                    activatecategories();

                                }
                            }.start();

                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonnext.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        login.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        insertdatavew.setVisibility(View.INVISIBLE);
                        insertdatavew2.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        login.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        final Button backtocategory = (Button) findViewById(R.id.button18);
        final CircularProgressBar timecircular = (CircularProgressBar) findViewById(R.id.circularprogressbar2);
        final Button backtocategory2 = (Button) findViewById(R.id.button181);
        timecircular.setMax(GAMEPLAYTIME);
        final RelativeLayout startrel = (RelativeLayout) findViewById(R.id.StartRelLayout);
        final RelativeLayout grid1 = (RelativeLayout) findViewById(R.id.grid1);
        final RelativeLayout grid2 = (RelativeLayout) findViewById(R.id.grid12);
        final FrameLayout textfram = (FrameLayout) findViewById(R.id.textfram);
        final RelativeLayout btnframe = (RelativeLayout) findViewById(R.id.Buttonframe);

        final Button backtocategory3 = (Button) findViewById(R.id.buttonbacksimp);
        final Button backtocategorymath = (Button) findViewById(R.id.button18color);
        final Button backtocategoryshooter = (Button) findViewById(R.id.button18shooter);

        final Button Bstart2 = (Button) findViewById(R.id.button312);
        final RelativeLayout Startlayout2 = (RelativeLayout) findViewById(R.id.StartRelLayout2);
        final RelativeLayout buttonframe2 = (RelativeLayout) findViewById(R.id.buttonframe);
        final FrameLayout textfram2 = (FrameLayout) findViewById(R.id.textfram222);

        final CircularProgressBar timecircular2 = (CircularProgressBar) findViewById(R.id.circularprogressbarsimplicity);
        timecircular2.setVisibility(View.INVISIBLE);


        final Button Bstartsort = (Button) findViewById(R.id.button31sort);

        final Button Bstartcircleshooter = (Button) findViewById(R.id.button31circleshooter);


        final CircularProgressBar timecircularsort = (CircularProgressBar) findViewById(R.id.circularprogressbarsorting);
        final Button backsort = (Button) findViewById(R.id.buttonbacksort);
        final LinearLayout startrelsort = (LinearLayout) findViewById(R.id.StartRelLayoutsorting);
        final RelativeLayout sortsixchioce = (RelativeLayout) findViewById(R.id.sixchoice);
        final RelativeLayout gridsort = (RelativeLayout) findViewById(R.id.grid12sorting);
        timecircularsort.setVisibility(View.INVISIBLE);
        timecircularsort.setMax(GAMEPLAYTIME2);

        b1int = (Button) findViewById(R.id.button11);
        b2int = (Button) findViewById(R.id.button21);
        b3int = (Button) findViewById(R.id.button311);
        b4int = (Button) findViewById(R.id.button41);
        b5int = (Button) findViewById(R.id.button51);
        b6int = (Button) findViewById(R.id.button61);

        b1int.setSoundEffectsEnabled(false);
        b2int.setSoundEffectsEnabled(false);
        b3int.setSoundEffectsEnabled(false);
        b4int.setSoundEffectsEnabled(false);
        b5int.setSoundEffectsEnabled(false);
        b6int.setSoundEffectsEnabled(false);


        //  b1int.setTypeface(tf);
        b1int.setTextSize(15);

        // b2int.setTypeface(tf);
        b2int.setTextSize(15);

        // b3int.setTypeface(tf);
        b3int.setTextSize(15);

        // b4int.setTypeface(tf);
        b4int.setTextSize(15);

        // b5int.setTypeface(tf);
        b5int.setTextSize(15);
        // b6int.setTypeface(tf);
        b6int.setTextSize(15);

        b1int.setBackground(getResources().getDrawable(R.drawable.segmentascending));
        b2int.setBackground(getResources().getDrawable(R.drawable.segmentascending));
        b3int.setBackground(getResources().getDrawable(R.drawable.segmentascending));
        b4int.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
        b5int.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
        b6int.setBackground(getResources().getDrawable(R.drawable.segmentdescending));


        b1 = (Button) findViewById(R.id.buttonsort);
        b2 = (Button) findViewById(R.id.button2sort);
        b3 = (Button) findViewById(R.id.button3sort);
        b4 = (Button) findViewById(R.id.button4sort);
        b5 = (Button) findViewById(R.id.button5sort);
        b6 = (Button) findViewById(R.id.button6sort);
        b7 = (Button) findViewById(R.id.button7sort);
        b8 = (Button) findViewById(R.id.button8sort);
        b9 = (Button) findViewById(R.id.button9sort);

        b1.setSoundEffectsEnabled(false);
        b2.setSoundEffectsEnabled(false);
        b3.setSoundEffectsEnabled(false);
        b4.setSoundEffectsEnabled(false);
        b5.setSoundEffectsEnabled(false);
        b6.setSoundEffectsEnabled(false);
        b7.setSoundEffectsEnabled(false);
        b8.setSoundEffectsEnabled(false);
        b9.setSoundEffectsEnabled(false);


        //  b1.setTypeface(tf);
        b1.setTextSize(15);

        // b2.setTypeface(tf);
        b2.setTextSize(15);

        // b3.setTypeface(tf);
        b3.setTextSize(15);

        //  b4.setTypeface(tf);
        b4.setTextSize(15);

        // b5.setTypeface(tf);
        b5.setTextSize(15);
        //  b6.setTypeface(tf);
        b6.setTextSize(15);
        //  b7.setTypeface(tf);
        b7.setTextSize(15);
        //  b8.setTypeface(tf);
        b8.setTextSize(15);
        //  b9.setTypeface(tf);
        b9.setTextSize(15);


        backsort.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backsort.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        points = 0;
                        level = 0;
                        timecircularsort.setVisibility(View.INVISIBLE);
                        if (waitTimersort != null) waitTimersort.cancel();

                        resetscore();
                        startrelsort.setVisibility(View.VISIBLE);

                        gridsort.setVisibility(View.INVISIBLE);
                        sortinglayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backsort.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backtocategory3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtocategory3.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        points = 0;
                        timecircular2.setVisibility(View.INVISIBLE);
                        if (waitTimer2 != null) waitTimer2.cancel();
                        resetscore();
                        textfram2.setVisibility(View.INVISIBLE);
                        Startlayout2.setVisibility(View.VISIBLE);
                        grid2.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortinglayout.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        buttonframe2.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtocategory3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backtocategory2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtocategory2.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        resetscore();
                        points = 0;
                        timecircular.setVisibility(View.INVISIBLE);
                        if (waitTimer != null) waitTimer.cancel();

                        textfram.setVisibility(View.INVISIBLE);
                        startrel.setVisibility(View.VISIBLE);
                        grid1.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        btnframe.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtocategory2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backtocategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtocategory.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        mathcategory.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtocategory.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backtocategorymath.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtocategorymath.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        resetscore();
                        colorcategory.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtocategorymath.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backtocategoryshooter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backtocategoryshooter.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        resetscore();
                        shootercategroy.setVisibility(View.INVISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backtocategoryshooter.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        bb1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bb1.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        mathcategory.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bb1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        bb2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bb2.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        colorcategory.setVisibility(View.VISIBLE);

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bb2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        bb3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        bb3.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        shootercategroy.setVisibility(View.VISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        bb3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        timecircular2.setMax(GAMEPLAYTIME2);

        final Button Bstartcolordec = (Button) findViewById(R.id.button31colordec);
        final CircularProgressBar colordeccirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarcolordec);
        final RelativeLayout startrelcolordec = (RelativeLayout) findViewById(R.id.StartRelLayoutcolordec);
        final RelativeLayout buttonframecolordec = (RelativeLayout) findViewById(R.id.linearLayout2);

        final RelativeLayout buttonframecolordec2 = (RelativeLayout) findViewById(R.id.Buttonframe4);
        final FrameLayout buttonframecolordec3 = (FrameLayout) findViewById(R.id.buttonframe3);
        final RelativeLayout gridcolordec = (RelativeLayout) findViewById(R.id.grid1colordec);
        colordeccirculatr.setVisibility(View.INVISIBLE);
        colordeccirculatr.setMax(GAMEPLAYTIME2);
        final Button backbuttoncolordec = (Button) findViewById(R.id.buttonbackcolordec);


        final Button backbuttonpickcolor = (Button) findViewById(R.id.buttonbackpickcolor);
        final Button Bstartpickcolor = (Button) findViewById(R.id.button331pickcolor);
        final CircularProgressBar pickcolorcirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarpickcolor);
        final RelativeLayout buttonframepickcolor = (RelativeLayout) findViewById(R.id.sixchoicepickcolor);
        final RelativeLayout startrelpickcolor = (RelativeLayout) findViewById(R.id.StartRelLayoutpickcol);
        final RelativeLayout gridpickcolor = (RelativeLayout) findViewById(R.id.grid1pickcolor);
        pickcolorcirculatr.setMax(GAMEPLAYTIME2);
        pickcolorcirculatr.setVisibility(View.INVISIBLE);


        final Button Bstartmemory = (Button) findViewById(R.id.button31memory);
        final Button backbuttonmemory = (Button) findViewById(R.id.buttonbackmemory);
        final CircularProgressBar memorycirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarmemory);
        memorycirculatr.setMax(GAMEPLAYTIMEMEMORY - 500);
        memorycirculatr.setVisibility(View.INVISIBLE);
        final TableLayout buttonframememory = (TableLayout) findViewById(R.id.TableLayout03);
        final LinearLayout startrelmemory = (LinearLayout) findViewById(R.id.StartRelLayoutmemory);
        final RelativeLayout gridmemory = (RelativeLayout) findViewById(R.id.grid1memory);

        final Button backbuttonmineweeeper = (Button) findViewById(R.id.buttonbackminesweeper);
        final Button Bstaratminesweeper = (Button) findViewById(R.id.button31minesweeper);
        final CircularProgressBar minesweepercirculatr = (CircularProgressBar) findViewById(R.id.circularprogressbarminesweeper);
        minesweepercirculatr.setMax(GAMEPLAYTIME2);
        minesweepercirculatr.setVisibility(View.INVISIBLE);
        final RelativeLayout buttonframeminesweeper = (RelativeLayout) findViewById(R.id.grid);
        final RelativeLayout startrelminesweeper = (RelativeLayout) findViewById(R.id.StartRelLayoutminesweeper);
        final RelativeLayout gridminesweeper = (RelativeLayout) findViewById(R.id.grid1minesweeper);

        final RelativeLayout startrelcircleshoooter = (RelativeLayout) findViewById(R.id.StartRelLayoutcircleshooter);

        final RelativeLayout gridshooter = (RelativeLayout) findViewById(R.id.grid1shooter);
        final RelativeLayout circleshooterlose = (RelativeLayout) findViewById(R.id.grid1shooter2);
        final Button backbuttoncolorshooter = (Button) findViewById(R.id.buttonbackcolorshooter);
        final GameView gameview = (GameView) findViewById(R.id.game);


        final Button Bstartcolorcalc = (Button) findViewById(R.id.button31cardcalculation);
        final RelativeLayout gridcardcalc = (RelativeLayout) findViewById(R.id.grid1cardcalculation);
        final Button backbuttoncolorcalc = (Button) findViewById(R.id.buttonbackcardcalculation);
        final CircularProgressBar cardcalculationcircular = (CircularProgressBar) findViewById(R.id.circularprogressbarcarcalculation);
        final RelativeLayout startrelcardcalculation = (RelativeLayout) findViewById(R.id.StartRelLayoutcardcalculation);
        final LinearLayout cardslayout = (LinearLayout) findViewById(R.id.cardslayout);
        final LinearLayout cardslayout2 = (LinearLayout) findViewById(R.id.cardslayout22);


        cardcalculationcircular.setMax(GAMEPLAYTIME2);
        cardcalculationcircular.setVisibility(View.INVISIBLE);


        final Button Bstartpettrivia = (Button) findViewById(R.id.button312pettrivia);
        final Button backbuttonpettrivia = (Button) findViewById(R.id.buttonbackpettrivia);
        final RelativeLayout gridpettrivia = (RelativeLayout) findViewById(R.id.grid1petrivia);
        final CircularProgressBar pettriviacircular = (CircularProgressBar) findViewById(R.id.circularprogressbarpettrivia);
        final RelativeLayout startrelpettrivia = (RelativeLayout) findViewById(R.id.StartRelLayout2pettrivia);
        final RelativeLayout pettriviaquestion = (RelativeLayout) findViewById(R.id.pettriviamain);
        pettriviacircular.setMax(GAMEPLAYTIME2);
        pettriviacircular.setVisibility(View.INVISIBLE);

        Bstartpettrivia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartpettrivia.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        wrongtries = 0;
                        gamescore[0] = 0;
                        correcttries = 0;
                        score = 0;




                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        pettriviaquestion.setVisibility(View.INVISIBLE);
                        startrelpettrivia.setVisibility(View.INVISIBLE);
                        pettriviacircular.setVisibility(View.VISIBLE);

                        waitTimerpettrivia = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));
                                int progress = (int) milsec;
                                pettriviacircular.setProgress((int) milsec);
                                pettriviacircular.setProgressColor(Color.WHITE);
                                pettriviacircular.setTitleColor(Color.WHITE);
                                pettriviacircular.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {
                                showZairepettrivia();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();
                            }
                        }.start();

                        startpettrivia();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartpettrivia.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        Bstartcolorcalc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartcolorcalc.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        wrongtries = 0;
                        gamescore[0] = 0;
                        correcttries = 0;
                        score = 0;

                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        cardslayout.setVisibility(View.INVISIBLE);
                        cardslayout2.setVisibility(View.INVISIBLE);
                        startrelcardcalculation.setVisibility(View.INVISIBLE);
                        cardcalculationcircular.setVisibility(View.VISIBLE);

                        waitTimercardcalculation = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScorememory();

                                int progress = (int) milsec;
                                cardcalculationcircular.setProgress((int) milsec);
                                cardcalculationcircular.setProgressColor(Color.WHITE);
                                cardcalculationcircular.setTitleColor(Color.WHITE);
                                cardcalculationcircular.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                showZairecolorcalculation();


                            }
                        }.start();
                        final RelativeLayout gv = (RelativeLayout) findViewById(R.id.grid);

                        startcolorcalculation();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartcolorcalc.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        backbuttonpettrivia.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttonpettrivia.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;

                        STAGEE = MENUSTAGE;


                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (waitTimerpettrivia != null) waitTimerpettrivia.cancel();
                        //shownzaire = false;
                        resetscore();

                        startrelpettrivia.setVisibility(View.VISIBLE);
                        pettriviaquestion.setVisibility(View.INVISIBLE);
                        gridpettrivia.setVisibility(View.INVISIBLE);
                        pettriviacircular.setVisibility(View.INVISIBLE);


                        pettrivialayout.setVisibility(View.INVISIBLE);
                        mathcalrelative.setVisibility(View.INVISIBLE);
                        circleshooterlayout.setVisibility(View.INVISIBLE);
                        minesweeperlayout.setVisibility(View.INVISIBLE);
                        memeorylayout.setVisibility(View.INVISIBLE);
                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttonpettrivia.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backbuttoncolorcalc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttoncolorcalc.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (waitTimercardcalculation != null) waitTimercardcalculation.cancel();
                        shownzaire = false;
                        resetscore();

                        startrelcardcalculation.setVisibility(View.VISIBLE);
                        cardslayout2.setVisibility(View.INVISIBLE);
                        cardslayout.setVisibility(View.INVISIBLE);
                        gridcardcalc.setVisibility(View.INVISIBLE);

                        mathcalrelative.setVisibility(View.INVISIBLE);
                        circleshooterlayout.setVisibility(View.INVISIBLE);
                        minesweeperlayout.setVisibility(View.INVISIBLE);
                        memeorylayout.setVisibility(View.INVISIBLE);
                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttoncolorcalc.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backbuttoncolorshooter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttoncolorshooter.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;
                        STAGEE = MENUSTAGE;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        if (waitTimercircleshooter != null) waitTimercircleshooter.cancel();

                        resetscore();
                        startrelcircleshoooter.setVisibility(View.VISIBLE);
                        shownzaire = false;

                        gameview.setVisibility(View.INVISIBLE);
                        gridshooter.setVisibility(View.INVISIBLE);
                        circleshooterlose.setVisibility(View.INVISIBLE);
                        circleshooterlayout.setVisibility(View.INVISIBLE);
                        minesweeperlayout.setVisibility(View.INVISIBLE);
                        memeorylayout.setVisibility(View.INVISIBLE);
                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttoncolorshooter.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backbuttonmineweeeper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttonmineweeeper.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        STAGEE = MENUSTAGE;
                        if (waitTimerminesweeper != null) waitTimerminesweeper.cancel();

                        resetscore();
                        startrelminesweeper.setVisibility(View.VISIBLE);
                        minesweepercirculatr.setVisibility(View.INVISIBLE);
                        buttonframeminesweeper.setVisibility(View.INVISIBLE);
                        gridminesweeper.setVisibility(View.INVISIBLE);

                        minesweeperlayout.setVisibility(View.INVISIBLE);
                        memeorylayout.setVisibility(View.INVISIBLE);
                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttonmineweeeper.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        mGameView = (GameView) findViewById(R.id.game);

        if (savedInstanceState != null) {
            //Log.i("frozen-bubble", "FrozenBubble.onCreate(...)");
        } else {
            //Log.i("frozen-bubble", "FrozenBubble.onCreate(null)");
        }
//        super.onCreate(savedInstanceState);
        ctx = this;
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);


        final RelativeLayout circleshooterwin = (RelativeLayout) findViewById(R.id.grid1shooter);
        final RelativeLayout circleshooterlose2 = (RelativeLayout) findViewById(R.id.grid1shooter2);


        circleshooterwin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        circleshooterlose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        Bstartcircleshooter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartcircleshooter.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        mGameView.setVisibility(View.VISIBLE);

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        wrongtries = 0;
                        gamescore[0] = 0;
                        correcttries = 0;
                        score = 0;

                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        startrelcircleshoooter.setVisibility(View.INVISIBLE);
                        circleshooterwin.setVisibility(View.INVISIBLE);
                        circleshooterlose2.setVisibility(View.INVISIBLE);
                        waitTimercircleshooter = new CountDownTimer(GAMEPLAYTIME, 100) {
                            public void onTick(long milsec) {

                                //int x =  mGameThread.mFrozenGame.levelManager.currentLevel;


                                Log.d("yigitsaybaba", Integer.toString(mGameThread.mFrozenGame.levelManager.currentLevel));
                                Log.d("yigitsaybaba", Boolean.toString(mGameThread.mFrozenGame.endOfGame));

                                Log.d("yigitsayendofgame", Boolean.toString(mGameThread.mFrozenGame.levelCompleted));


                                if (mGameThread.mFrozenGame.endOfGame) {

                                    if (mGameThread.mFrozenGame.levelCompleted)

                                        circleshooterwin.setVisibility(View.VISIBLE);
                                    else circleshooterlose.setVisibility(View.VISIBLE);


                                    if (!shownzaire) {
                                        showZairecirclehooter();
                                        adcount++;
                                        editor.putInt("adcount", adcount);
                                        editor.commit();
                                        new CountDownTimer(300, 100) {
                                            public void onTick(long millisUntilFinished) {
                                            }

                                            public void onFinish() {
                                                if (adcount % maxAD == 0 && !adflag) showad();
                                            }

                                        }.start();
                                    }

                                }


                            }

                            public void onFinish() {


                                // showZaireminesweeper();

                            }

                        }.start();


                        startcircleshooter();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartcircleshooter.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        Bstaratminesweeper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstaratminesweeper.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        wrongtries = 0;
                        gamescore[0] = 0;
                        correcttries = 0;
                        score = 0;

                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        startrelminesweeper.setVisibility(View.INVISIBLE);
                        buttonframeminesweeper.setVisibility(View.VISIBLE);
                        minesweepercirculatr.setVisibility(View.VISIBLE);

                        waitTimerminesweeper = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScorememory();

                                int progress = (int) milsec;
                                minesweepercirculatr.setProgress((int) milsec);
                                minesweepercirculatr.setProgressColor(Color.WHITE);
                                minesweepercirculatr.setTitleColor(Color.WHITE);
                                minesweepercirculatr.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                showZaireminesweeper();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();


                            }
                        }.start();
                        final RelativeLayout gv = (RelativeLayout) findViewById(R.id.grid);
                        gv.removeAllViews();
                        startminesweeper();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstaratminesweeper.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        backbuttonmemory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttonmemory.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;
                        STAGEE = MENUSTAGE;
                        memorycirculatr.setVisibility(View.INVISIBLE);
                        if (waitTimermemory != null) waitTimermemory.cancel();

                        resetscore();
                        startrelmemory.setVisibility(View.VISIBLE);

                        buttonframememory.setVisibility(View.INVISIBLE);
                        gridmemory.setVisibility(View.INVISIBLE);

                        memeorylayout.setVisibility(View.INVISIBLE);
                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttonmemory.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        Bstartmemory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartpickcolor.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        wrongtries = 0;
                        gamescore[0] = 0;
                        correcttries = 0;
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        startrelmemory.setVisibility(View.INVISIBLE);
                        buttonframememory.setVisibility(View.VISIBLE);
                        memorycirculatr.setVisibility(View.VISIBLE);
                        // memorylevelmanager();
                        waitTimermemory = new CountDownTimer(GAMEPLAYTIMEMEMORY, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScorememory();

                                int progress = (int) milsec;
                                memorycirculatr.setProgress((int) milsec);
                                memorycirculatr.setProgressColor(Color.WHITE);
                                memorycirculatr.setTitleColor(Color.WHITE);
                                memorycirculatr.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                showZairememory();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();


                            }
                        }.start();
                        startmemory();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartpickcolor.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        backbuttonpickcolor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttonpickcolor.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;
                        pickcolorcirculatr.setVisibility(View.INVISIBLE);
                        if (waitTimerpickcolor != null) waitTimerpickcolor.cancel();
                        STAGEE = MENUSTAGE;
                        resetscore();
                        startrelpickcolor.setVisibility(View.VISIBLE);

                        buttonframepickcolor.setVisibility(View.INVISIBLE);
                        gridpickcolor.setVisibility(View.INVISIBLE);

                        pickcolorlayout.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttonpickcolor.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        backbuttoncolordec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        backbuttoncolordec.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        score = 0;
                        points = 0;
                        level = 0;
                        tries = 0;
                        colordeccirculatr.setVisibility(View.INVISIBLE);
                        if (waitTimercolordec != null) waitTimercolordec.cancel();
                        STAGEE = MENUSTAGE;
                        resetscore();
                        startrelcolordec.setVisibility(View.VISIBLE);
                        buttonframecolordec.setVisibility(View.INVISIBLE);
                        buttonframecolordec2.setVisibility(View.INVISIBLE);
                        buttonframecolordec3.setVisibility(View.INVISIBLE);
                        gridcolordec.setVisibility(View.INVISIBLE);
                        coloerdeclayout.setVisibility(View.INVISIBLE);
                        simplicitylayout.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.INVISIBLE);
                        operationlayout.setVisibility(View.INVISIBLE);
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        backbuttoncolordec.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        Bstartpickcolor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartpickcolor.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        startrelpickcolor.setVisibility(View.INVISIBLE);
                        buttonframepickcolor.setVisibility(View.VISIBLE);
                        pickcolorcirculatr.setVisibility(View.VISIBLE);

                        waitTimerpickcolor = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScorepickcolor();

                                int progress = (int) milsec;
                                pickcolorcirculatr.setProgress((int) milsec);
                                pickcolorcirculatr.setProgressColor(Color.WHITE);
                                pickcolorcirculatr.setTitleColor(Color.WHITE);
                                pickcolorcirculatr.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                finishgame = true;
                                showZairepickcolor();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();


                            }
                        }.start();
                        startpickcolor();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartpickcolor.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        Bstartcolordec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartcolordec.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        finishgame = false;
                        tries = 0;
                        level = 0;
                        gamecount--;
                        updategamecount();
                        editor.putInt("gamecount", gamecount);
                        editor.commit();

                        final Button b1 = (Button) findViewById(R.id.choice1);
                        final Button b2 = (Button) findViewById(R.id.choice2);

                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        startrelcolordec.setVisibility(View.INVISIBLE);
                        buttonframecolordec.setVisibility(View.VISIBLE);
                        colordeccirculatr.setVisibility(View.VISIBLE);

                        waitTimercolordec = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScorecolordec();

                                int progress = (int) milsec;
                                colordeccirculatr.setProgress((int) milsec);
                                colordeccirculatr.setProgressColor(Color.WHITE);
                                colordeccirculatr.setTitleColor(Color.WHITE);
                                colordeccirculatr.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                finishgame = true;
                                showZairecolordec();
                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();

                            }
                        }.start();
                        startcoldec();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartcolordec.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        updateScoresort();

        Bstartsort.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstartsort.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        gamecount--;
                        updategamecount();

                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        startrelsort.setVisibility(View.INVISIBLE);
                        sortsixchioce.setVisibility(View.VISIBLE);
                        timecircularsort.setVisibility(View.VISIBLE);
                        waitTimersort = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                updateScoresort();

                                int progress = (int) milsec;
                                timecircularsort.setProgress((int) milsec);
                                timecircularsort.setProgressColor(Color.WHITE);
                                timecircularsort.setTitleColor(Color.WHITE);
                                timecircularsort.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                showZairesort();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();


                            }
                        }.start();
                        start3();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstartsort.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        Bstart2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstart2.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);
                        gamecount--;
                        updategamecount();

                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        Startlayout2.setVisibility(View.INVISIBLE);
                        buttonframe2.setVisibility(View.VISIBLE);
                        textfram2.setVisibility(View.VISIBLE);
                        timecircular2.setVisibility(View.VISIBLE);

                        waitTimer2 = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                Long time = milsec / 1000;
                                //Time.setText(Long.toString(time));


                                int progress = (int) milsec;
                                timecircular2.setProgress((int) milsec);
                                timecircular2.setProgressColor(Color.WHITE);
                                timecircular2.setTitleColor(Color.WHITE);
                                timecircular2.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {

                                showZaire2();

                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();


                            }
                        }.start();
                        start2();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstart2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        //  final ProgressBar pr1 =(ProgressBar) findViewById(R.id.progressBar2);
        final Button Bstart = (Button) findViewById(R.id.button31);
        final RelativeLayout Startlayout = (RelativeLayout) findViewById(R.id.StartRelLayout);
        final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.Buttonframe);

        timecircular.setMax(GAMEPLAYTIME2);
        final Context con = this;
        timecircular.setVisibility(View.INVISIBLE);
        Bstart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        Bstart.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        gamecount--;
                        updategamecount();
                        if (soundflag) soundPool.play(soundID, volume, volume, 1, 0, 1f);

                        editor.putInt("gamecount", gamecount);
                        editor.commit();


                        Startlayout.setVisibility(View.INVISIBLE);
                        buttonframe.setVisibility(View.VISIBLE);
                        textfram.setVisibility(View.VISIBLE);
                        timecircular.setVisibility(View.VISIBLE);


                        waitTimer = new CountDownTimer(GAMEPLAYTIME, 10) {
                            public void onTick(long milsec) {
                                time = milsec / 1000;
                                //Time.setText(Long.toString(time));

                                int progress = (int) milsec;

                                timecircular.setProgress((int) milsec);
                                timecircular.setProgressColor(Color.WHITE);
                                timecircular.setTitleColor(Color.WHITE);
                                timecircular.setTitle(Long.toString(time));
                                // pr1.setDrawingCacheBackgroundColor(Color.GREEN);
                            }

                            public void onFinish() {


                                showZaire();
                                adcount++;
                                editor.putInt("adcount", adcount);
                                editor.commit();
                                new CountDownTimer(300, 100) {
                                    public void onTick(long millisUntilFinished) {
                                    }

                                    public void onFinish() {
                                        if (adcount % maxAD == 0 && !adflag) showad();
                                    }

                                }.start();

                            }
                        }.start();
                        start();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        Bstart.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


    }

    Context mContext = this;

    void startcircleshooter()

    {

        //SessionMAndroidConfig.getInstance().setServer(SessionMAndroidConfig.SERVER_DEV);
        //SessionM.getInstance().startSession(this, "");

        // Allow editor functionalities.
        Intent i = getIntent();
        if (null == i || null == i.getExtras() ||
                !i.getExtras().containsKey("levels")) {
            // Default intent.
            activityCustomStarted = false;


        } else {
            // Get custom level last played.
            SharedPreferences sp = getSharedPreferences(
                    FrozenBubble.PREFS_NAME, Context.MODE_PRIVATE);
            int startingLevel = sp.getInt("levelCustom", 0);
            int startingLevelIntent = i.getIntExtra("startingLevel", -2);
            startingLevel = (startingLevelIntent == -2) ?
                    startingLevel : startingLevelIntent;
            activityCustomStarted = true;
            mGameView = new GameView(this, i.getExtras().getByteArray("levels"),
                    startingLevel);
            //setContentView(mGameView);
        }

        byte[] xx;
        try {
            InputStream is = mContext.getAssets().open("levels.txt");
            int size = is.available();
            byte[] levels = new byte[size];
            is.read(levels);
            is.close();

            xx = levels;
        } catch (IOException e) {
            // Should never happen.
            throw new RuntimeException(e);
        }


//RelativeLayout r = (RelativeLayout) findViewById(R.id.shooterlayout);


        //      mGameView = new GameView(this, xx,
        //            20);

        //  r.addView(mGameView);

        mGameThread = mGameView.getThread();
        // mGameThread.mFrozenGame.levelManager.setcurrentLevel(25);
        mGameThread.newGame(mathlevel);
        mGameView.requestFocus();
        setFullscreen();


    }

    void start3col() {

        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);


        String colortext[] = {GREEN, PINK, RED, BLUE, WHITE, YELLOW, PURPLE, ORANGE};
        RelativeLayout button2layout = (RelativeLayout) findViewById(R.id.linearLayout2);
        FrameLayout button3layout = (FrameLayout) findViewById(R.id.buttonframe3);

        if (finishgame == true) {
            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
            //final TextView t1 = (TextView) findViewById(R.id.textView);
            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);

        } else {


            button2layout.setVisibility(View.INVISIBLE);
            button3layout.setVisibility(View.VISIBLE);
        }


        // final TextView scoretext = (TextView) findViewById(R.id.score);
        // scoretext.setText(Integer.toString(score));


        // final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        // shake.setDuration(200);
        final Button b1 = (Button) findViewById(R.id.choice11);
        final Button b2 = (Button) findViewById(R.id.choice21);
        final Button b3 = (Button) findViewById(R.id.choice3);

      /*  b1.setTypeface(tf);
        b1.setTextSize(30);
        b2.setTypeface(tf);
        b2.setTextSize(30);
        b3.setTypeface(tf);
        b3.setTextSize(30);*/

        boolean blocked = false;
        final Handler handler = new Handler();

        barray2[0] = b1;
        barray2[1] = b2;
        barray2[2] = b3;

        Random r = new Random();
        Random r1 = new Random();
        Random r2 = new Random();
        Random r3 = new Random();

        int min = 0;
        int max = 7;
        int counter = r.nextInt((max - min) + 1) + min;
        int counterwrong = r1.nextInt((max - min) + 1) + min;
        int counterwrongtext = r2.nextInt((max - min) + 1) + min;
        int counter2 = r3.nextInt((max - min) + 1) + min;


        while (counter == counterwrong | counter == counterwrongtext | counterwrong == counterwrongtext | counter2 == counter | counter2 == counterwrongtext | counter2 == counterwrong)


        {

            Random r11 = new Random();
            Random r12 = new Random();
            Random r13 = new Random();
            Random r14 = new Random();
            min = 0;
            max = 7;
            counter = r11.nextInt((max - min) + 1) + min;
            counterwrong = r12.nextInt((max - min) + 1) + min;
            counterwrongtext = r13.nextInt((max - min) + 1) + min;
            counter2 = r14.nextInt((max - min) + 1) + min;
        }

        Random r111 = new Random();
        randomcounter = r111.nextInt((2 - 0) + 1) + 0;

        Button other;
        Button real2;

        if (randomcounter == 0)

        {
            other = b3;
            real2 = b2;
        } else if (randomcounter == 1)

        {
            other = b1;
            real2 = b3;
        } else {
            other = b2;
            real2 = b1;
        }
        ;


        barray2[randomcounter].setBackgroundColor(colorarray[counter]);
        barray2[randomcounter].setText(colortext[counter]);

        if (colorarray[counter] == Color.WHITE | colorarray[counter] == Color.YELLOW) {
            barray2[randomcounter].setTextColor(Color.BLACK);
        } else barray2[randomcounter].setTextColor(Color.WHITE);


        other.setBackgroundColor(colorarray[counterwrong]);
        other.setText(colortext[counterwrongtext]);

        if (colorarray[counterwrong] == Color.WHITE | colorarray[counterwrong] == Color.YELLOW) {
            other.setTextColor(Color.BLACK);
        } else other.setTextColor(Color.WHITE);


        real2.setBackgroundColor(colorarray[counter2]);
        real2.setText(colortext[counter2]);

        if (colorarray[counter2] == Color.WHITE | colorarray[counter2] == Color.YELLOW) {
            real2.setTextColor(Color.BLACK);
        } else real2.setTextColor(Color.WHITE);


        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;


                        } else {

                            wrong++;
                            vibrate(150);
                            //b1.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                        tries++;
                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {

                                if (tries <= COLORDECSECONDLEVEL & tries > COLORDECFIRSTLEVEL) {

                                    start3col();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 2) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;


                        } else {
                            wrong++;
                            vibrate(150);
                            //b2.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                        tries++;
                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries <= COLORDECSECONDLEVEL & tries > COLORDECFIRSTLEVEL) {

                                    start3col();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 0) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;


                        } else {
                            wrong++;
                            //b3.startAnimation(shake);
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }
                        tries++;
                        b3.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries <= COLORDECSECONDLEVEL & tries > COLORDECFIRSTLEVEL) {


                                    start3col();

                                } else {
                                    level++;
                                    start4();
                                }
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    }

    public void colordeclevelmanager()
    {

        switch(mathlevel)

        {

            case 0:
                COLORDECFIRSTLEVEL = 30;
                COLORDECSECONDLEVEL = 50;

                return;
            case 1:
                COLORDECFIRSTLEVEL = 25;
                COLORDECSECONDLEVEL = 45;

                return;
            case 2:
                COLORDECFIRSTLEVEL = 24;
                COLORDECSECONDLEVEL = 44;

                return;
            case 3:
                COLORDECFIRSTLEVEL = 22;
                COLORDECSECONDLEVEL = 42;

                return;
            case 4:
                COLORDECFIRSTLEVEL = 20;
                COLORDECSECONDLEVEL = 40;

                return;
            case 5:
                COLORDECFIRSTLEVEL = 19;
                COLORDECSECONDLEVEL = 38;

                return;
            case 6:
                COLORDECFIRSTLEVEL = 17;
                COLORDECSECONDLEVEL = 33;

                return;
            case 7:
                COLORDECFIRSTLEVEL = 16;
                COLORDECSECONDLEVEL = 32;

                return;
            case 8:
                COLORDECFIRSTLEVEL = 15;
                COLORDECSECONDLEVEL = 32;

                return;
            case 9:
                COLORDECFIRSTLEVEL = 14;
                COLORDECSECONDLEVEL = 30;

                return;
            case 10:
                COLORDECFIRSTLEVEL = 12;
                COLORDECSECONDLEVEL = 28;

                return;

        }

        if (mathlevel <= 15  && mathlevel > 10)   {

            COLORDECFIRSTLEVEL = 10;
            COLORDECSECONDLEVEL = 24;
        }

        else  if (mathlevel <= 25  && mathlevel > 15)   {

            COLORDECFIRSTLEVEL = 9;
            COLORDECSECONDLEVEL = 20;
        }

        else  if (mathlevel <= 35  && mathlevel > 25)   {

            COLORDECFIRSTLEVEL = 6;
            COLORDECSECONDLEVEL = 17;
        }

        else  if (mathlevel > 35  )   {

            COLORDECFIRSTLEVEL = 3;
            COLORDECSECONDLEVEL = 15;
        }



    }




    void start4() {
        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);

        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
        RelativeLayout button2layout = (RelativeLayout) findViewById(R.id.linearLayout2);
        RelativeLayout frame4 = (RelativeLayout) findViewById(R.id.Buttonframe4);
        FrameLayout button3layout = (FrameLayout) findViewById(R.id.buttonframe3);
        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);


        String colortext[] = {GREEN, PINK, RED, BLUE, WHITE, YELLOW, PURPLE, ORANGE};
        if (finishgame == true) {

            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            frame4.setVisibility(View.INVISIBLE);

        } else {

            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);
            frame4.setVisibility(View.VISIBLE);
        }

        //final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        // shake.setDuration(200);

        final Button b1 = (Button) findViewById(R.id.btnselect1);
        final Button b2 = (Button) findViewById(R.id.btnselect2);
        final Button b3 = (Button) findViewById(R.id.btnselect3);
        final Button b4 = (Button) findViewById(R.id.btnselect4);


       /* b1.setTypeface(tf);
        b1.setTextSize(30);
        b2.setTypeface(tf);
        b2.setTextSize(30);
        b3.setTypeface(tf);
        b3.setTextSize(30);

        b4.setTypeface(tf);
        b4.setTextSize(30);*/

        Random r = new Random();


        int min = 0;
        int max = 3;
        int x11 = r.nextInt((max - min) + 1) + min;

        final int[] counter = {x11};
        if (counter[0] == 0) {

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);


            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1) - 1]);
            b3.setBackgroundColor(colorarray[number.get(2) - 1]);
            b4.setBackgroundColor(colorarray[number.get(3) - 1]);


            if (colorarray[number.get(0) - 1] == Color.WHITE | colorarray[number.get(0) - 1] == Color.YELLOW) {
                b1.setTextColor(Color.BLACK);
            } else b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1] == Color.WHITE | colorarray[number.get(1) - 1] == Color.YELLOW) {
                b2.setTextColor(Color.BLACK);
            } else b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1] == Color.WHITE | colorarray[number.get(2) - 1] == Color.YELLOW) {
                b3.setTextColor(Color.BLACK);
            } else b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1] == Color.WHITE | colorarray[number.get(3) - 1] == Color.YELLOW) {
                b4.setTextColor(Color.BLACK);
            } else b4.setTextColor(Color.WHITE);


            b1.setText(colortext[number.get(7) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(3) - 1]);


        } else if (counter[0] == 1) {


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1) - 1]);
            b3.setBackgroundColor(colorarray[number.get(2) - 1]);
            b4.setBackgroundColor(colorarray[number.get(3) - 1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(7) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(3) - 1]);

            if (colorarray[number.get(0) - 1] == Color.WHITE | colorarray[number.get(0) - 1] == Color.YELLOW) {
                b1.setTextColor(Color.BLACK);
            } else b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1] == Color.WHITE | colorarray[number.get(1) - 1] == Color.YELLOW) {
                b2.setTextColor(Color.BLACK);
            } else b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1] == Color.WHITE | colorarray[number.get(2) - 1] == Color.YELLOW) {
                b3.setTextColor(Color.BLACK);
            } else b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1] == Color.WHITE | colorarray[number.get(3) - 1] == Color.YELLOW) {
                b4.setTextColor(Color.BLACK);
            } else b4.setTextColor(Color.WHITE);

        } else if (counter[0] == 2) {


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1) - 1]);
            b3.setBackgroundColor(colorarray[number.get(2) - 1]);
            b4.setBackgroundColor(colorarray[number.get(3) - 1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(7) - 1]);
            b4.setText(colortext[number.get(3) - 1]);

            if (colorarray[number.get(0) - 1] == Color.WHITE | colorarray[number.get(0) - 1] == Color.YELLOW) {
                b1.setTextColor(Color.BLACK);
            } else b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1] == Color.WHITE | colorarray[number.get(1) - 1] == Color.YELLOW) {
                b2.setTextColor(Color.BLACK);
            } else b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1] == Color.WHITE | colorarray[number.get(2) - 1] == Color.YELLOW) {
                b3.setTextColor(Color.BLACK);
            } else b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1] == Color.WHITE | colorarray[number.get(3) - 1] == Color.YELLOW) {
                b4.setTextColor(Color.BLACK);
            } else b4.setTextColor(Color.WHITE);

        } else {

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 1; i <= 8; ++i) number.add(i);
            Collections.shuffle(number);

            b1.setBackgroundColor(colorarray[number.get(0) - 1]);
            b2.setBackgroundColor(colorarray[number.get(1) - 1]);
            b3.setBackgroundColor(colorarray[number.get(2) - 1]);
            b4.setBackgroundColor(colorarray[number.get(3) - 1]);

            b1.setText(colortext[number.get(0) - 1]);
            b2.setText(colortext[number.get(1) - 1]);
            b3.setText(colortext[number.get(2) - 1]);
            b4.setText(colortext[number.get(7) - 1]);


            if (colorarray[number.get(0) - 1] == Color.WHITE | colorarray[number.get(0) - 1] == Color.YELLOW) {
                b1.setTextColor(Color.BLACK);
            } else b1.setTextColor(Color.WHITE);


            if (colorarray[number.get(1) - 1] == Color.WHITE | colorarray[number.get(1) - 1] == Color.YELLOW) {
                b2.setTextColor(Color.BLACK);
            } else b2.setTextColor(Color.WHITE);

            if (colorarray[number.get(2) - 1] == Color.WHITE | colorarray[number.get(2) - 1] == Color.YELLOW) {
                b3.setTextColor(Color.BLACK);
            } else b3.setTextColor(Color.WHITE);


            if (colorarray[number.get(3) - 1] == Color.WHITE | colorarray[number.get(3) - 1] == Color.YELLOW) {
                b4.setTextColor(Color.BLACK);
            } else b4.setTextColor(Color.WHITE);


        }
        final Handler handler = new Handler();
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
        b4.setEnabled(true);


        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 0) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }

                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 2) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b3.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


        b4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.Buttonframe4);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);
                        }
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b4.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (counter[0] == 3) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;
                        } else {

                            wrong++;
                            vibrate(150);
                            score = score - 10;
                            if (score <= 0) score = 0;
                        }
                        b4.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                start4();
                            }
                        }, delay); //10sec delay
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b4.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    }


    int COLORDECFIRSTLEVEL= 3;
    int COLORDECSECONDLEVEL= 3;


    void startcoldec() {

        String GREEN = getString(R.string.GREEN);
        String PINK = getString(R.string.PINK);
        String RED = getString(R.string.RED);
        String BLUE = getString(R.string.BLUE);
        String WHITE = getString(R.string.WHITE);
        String YELLOW = getString(R.string.YELLOW);
        String PURPLE = getString(R.string.PURPLE);
        String ORANGE = getString(R.string.ORANGE);


        String colortext[] = {GREEN, PINK, RED, BLUE, WHITE, YELLOW, PURPLE, ORANGE};
        final Button b1 = (Button) findViewById(R.id.choice1);
        final Button b2 = (Button) findViewById(R.id.choice2);

        if (finishgame == true) {
            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
            //final TextView t1 = (TextView) findViewById(R.id.textView);
            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
            l3.setVisibility(View.INVISIBLE);
            f3.setVisibility(View.INVISIBLE);

        }


        //b1.setTypeface(tf);
        //b1.setTextSize(30);
        //b2.setTypeface(tf);
        //b2.setTextSize(30);


        barray[0] = b1;
        barray[1] = b2;
        Random r = new Random();
        Random r1 = new Random();
        Random r2 = new Random();
        int min = 0;
        int max = 7;
        int counter = r.nextInt((max - min) + 1) + min;
        int counterwrong = r1.nextInt((max - min) + 1) + min;
        int counterwrongtext = r2.nextInt((max - min) + 1) + min;
        while (counter == counterwrong | counter == counterwrongtext | counterwrong == counterwrongtext)


        {

            Random r11 = new Random();
            Random r12 = new Random();
            Random r13 = new Random();
            min = 0;
            max = 7;
            counter = r11.nextInt((max - min) + 1) + min;
            counterwrong = r12.nextInt((max - min) + 1) + min;
            counterwrongtext = r13.nextInt((max - min) + 1) + min;
        }

        Random r111 = new Random();
        randomcounter = r111.nextInt((1 - 0) + 1) + 0;

        Button other;

        if (randomcounter == 0) other = b2;
        else other = b1;


        barray[randomcounter].setBackgroundColor(colorarray[counter]);
        barray[randomcounter].setText(colortext[counter]);

        if (colorarray[counter] == Color.WHITE | colorarray[counter] == Color.YELLOW) {
            barray[randomcounter].setTextColor(Color.BLACK);
        } else barray[randomcounter].setTextColor(Color.WHITE);

        boolean blocked = false;
        final Handler handler = new Handler();
        other.setBackgroundColor(colorarray[counterwrong]);
        other.setText(colortext[counterwrongtext]);

        if (colorarray[counterwrong] == Color.WHITE | colorarray[counterwrong] == Color.YELLOW) {
            other.setTextColor(Color.BLACK);
        } else other.setTextColor(Color.WHITE);

        // final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        //shake.setDuration(200);
        b1.setEnabled(true);
        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:


                        if (randomcounter == 1) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            score = score + 50;


                        } else {
                            vibrate(150);
                            wrong++;
                            //b1.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }

                        //  final TextView scoretext = (TextView) findViewById(R.id.score);

                        tries++;
                        b1.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries < COLORDECFIRSTLEVEL) {

                                    startcoldec();

                                } else {
                                    level++;
                                    start3col();
                                }
                            }
                        }, delay); //10sec delay


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }


                }
                return true;
            }
        });
        b2.setEnabled(true);
        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        if (finishgame == true) {
                            final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
                            //final TextView t1 = (TextView) findViewById(R.id.textView);
                            final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
                            l3.setVisibility(View.INVISIBLE);
                            f3.setVisibility(View.INVISIBLE);

                        }

                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        b2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (randomcounter == 0) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            if (level == 1)
                                score = score + 50;
                            else score = score + 50;


                        } else {
                            vibrate(150);
                            wrong++;
                            //b2.startAnimation(shake);
                            score = score - 10;
                            if (score <= 0) score = 0;

                        }
                        // final TextView scoretext = (TextView) findViewById(R.id.score);
                        // scoretext.setText(Integer.toString(score));
                        tries++;

                        b2.setEnabled(false);
                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                if (tries < COLORDECFIRSTLEVEL) {

                                    startcoldec();

                                } else {
                                    level++;
                                    start3col();
                                }
                            }
                        }, delay); //10sec delay

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        b2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }


                }
                return true;
            }
        });


    }


    public void startpettrivia()

    {
        final ImageView questionimg = (ImageView) findViewById(R.id.imageViewknowledge);
        final Button knowledge1 = (Button) findViewById(R.id.button3know);
        final Button knowledge2 = (Button) findViewById(R.id.button2know);
        final Button knowledge3 = (Button) findViewById(R.id.button1know);

        final RelativeLayout pettriviaquestion = (RelativeLayout) findViewById(R.id.pettriviamain);

        pettriviaquestion.setVisibility(View.VISIBLE);
        Random r = new Random();


        //************ test




         //************ test

        int correct1 = r.nextInt(question.length - MIN) + MIN;
        int incorrect1 = r.nextInt(question.length - MIN) + MIN;
        int incorrect2 = r.nextInt(question.length - MIN) + MIN;

        while (correct1 == incorrect1 || correct1 == incorrect2 || incorrect1 == incorrect2) {

            correct1 = r.nextInt(question.length - MIN) + MIN;
            incorrect1 = r.nextInt(question.length - MIN) + MIN;
            incorrect2 = r.nextInt(question.length - MIN) + MIN;
        }


        final int randbox = r.nextInt(3 - 0) + 0;

        questionimg.setBackgroundResource(mBitmapIds[correct1]);

        final TextView t = (TextView) findViewById(R.id.textView2);

        Log.d("String:", question[correct1]);
        Log.d("count:", Integer.toString(correct1));

        Log.d("Lenght:", Integer.toString(question.length));
        Log.d("Lenght:", Integer.toString(mBitmapIds.length));

        if (randbox == 0) {

            knowledge1.setText(question[correct1]);
            knowledge2.setText(question[incorrect1]);
            knowledge3.setText(question[incorrect2]);

        } else if (randbox == 1) {

            knowledge1.setText(question[incorrect1]);
            knowledge2.setText(question[correct1]);
            knowledge3.setText(question[incorrect2]);
        } else if (randbox == 2) {

            knowledge1.setText(question[incorrect1]);
            knowledge2.setText(question[incorrect2]);
            knowledge3.setText(question[correct1]);
        }


        knowledge1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randbox == 0) {

                    score += 50;

                    final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    if (soundflag) {
                        clickaudio.start();
                        clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();

                            }

                            ;
                        });
                    }


                } else

                {
                    vibrate(200);
                    score -= 5;

                }
                updatescorepettriva();
                startpettrivia();
            }
        });


        knowledge2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randbox == 1) {

                    score += 50;
                    final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    if (soundflag) {
                        clickaudio.start();
                        clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();

                            }

                            ;
                        });
                    }


                } else {
                    vibrate(200);
                    score -= 5;

                }
                updatescorepettriva();
                startpettrivia();
            }
        });


        knowledge3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (randbox == 2) {

                    score += 50;

                    final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                    if (soundflag) {
                        clickaudio.start();
                        clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mp.release();

                            }

                            ;
                        });
                    }


                } else {
                    vibrate(200);
                    score -= 5;

                }
                updatescorepettriva();
                startpettrivia();
            }
        });
    }

    public void updatescorepettriva()

    {
        final TextView text = (TextView) findViewById(R.id.textView10pettrivia);

        if (score < 0) score = 0;


        text.setText(Integer.toString(score));


    }

    public void showZairepettrivia()

    {

        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1petrivia);

        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.pettriviamain);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarpettrivia);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);


        new CountDownTimer(1000, 10) {

            public void onTick(long millisUntilFinished) {
                l3.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {

            }

        }.start();


        if (points < lowest | lowest == 0) lowest = score;
        if (points > highestcolor) highestcolor = score;


        scorecounter = scorecounter + score;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");

            winfunc();
            mathlevel++;
            scorecounter = scorecounter - LEVELMAX;

        }


        calculatedistance();


        updatelevel();

        int x = points;
        Intent i = new Intent();


        g.setVisibility(View.VISIBLE);
        l3.setVisibility(View.INVISIBLE);

        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }

    public void calculatedistance()

    {
        movehouses();
/*
        Float density = Resources.getSystem().getDisplayMetrics().density;
        FrameLayout frame = (FrameLayout) findViewById(R.id.quardywalkinglayout);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) (150 * density), (int) (150 * density));
        float distance = 80 * (scorecounter * 100 / LEVELMAX) / 100 + 25;
        lp.setMargins((int) (density * distance), 0, 0, (int) (density * -5));
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        frame.setLayoutParams(lp);
*/

    }


    public void showZairecirclehooter()

    {


        shownzaire = true;


        if (waitTimercircleshooter != null) waitTimercircleshooter.cancel();

        final GameView l3 = (GameView) findViewById(R.id.game);
        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1shooter);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        score = 1000;
        TextView text = (TextView) findViewById(R.id.textView10colorshooter);

        text.setText(Integer.toString(score));

        if (points < lowest | lowest == 0) lowest = score;
        if (points > highestcolor) highestcolor = score;


        scorecounter = scorecounter + score;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        calculatedistance();
        updatelevel();

        int x = points;
        Intent i = new Intent();


        g.setVisibility(View.VISIBLE);
        // l3.setVisibility(View.INVISIBLE);

        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();

    }

    public void showZairecolorcalculation()

    {

        final LinearLayout cardslayout = (LinearLayout) findViewById(R.id.cardslayout);
        final LinearLayout cardslayout2 = (LinearLayout) findViewById(R.id.cardslayout22);


        new CountDownTimer(1000, 10) {

            public void onTick(long millisUntilFinished) {
                cardslayout2.setVisibility(View.INVISIBLE);
                cardslayout.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {

            }

        }.start();

        final CircularProgressBar c = (CircularProgressBar) findViewById(R.id.circularprogressbarcarcalculation);


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1cardcalculation);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        if (points < lowest | lowest == 0) lowest = score;
        if (points > highestcolor) highestcolor = score;


        scorecounter = scorecounter + score;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        calculatedistance();
        updatelevel();

        int x = points;
        Intent i = new Intent();


        c.setVisibility(View.INVISIBLE);
        cardslayout.setVisibility(View.INVISIBLE);
        cardslayout2.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }

    public void showZaireminesweeper() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1minesweeper);

        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.grid);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarminesweeper);

        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);


        if (points < lowest | lowest == 0) lowest = score;
        if (points > highestcolor) highestcolor = score;


        scorecounter = scorecounter + score;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        calculatedistance();
        updatelevel();

        int x = points;
        Intent i = new Intent();


        g.setVisibility(View.VISIBLE);
        l3.setVisibility(View.INVISIBLE);

        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }


    public void showZairepickcolor() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1pickcolor);

        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.sixchoicepickcolor);
        //final TextView t1 = (TextView) findViewById(R.id.textView);


        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarpickcolor);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);

        points = points - (wrong * 10);
        if (points < lowest | lowest == 0) lowest = points;
        if (points > highestcolor) highestcolor = points;


        scorecounter = scorecounter + points;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }
        calculatedistance();
        updatelevel();

        int x = points;
        Intent i = new Intent();


        g.setVisibility(View.VISIBLE);
        l3.setVisibility(View.INVISIBLE);

        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }


    public void showZairecolordec() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1colordec);

        final RelativeLayout l3 = (RelativeLayout) findViewById(R.id.linearLayout2);
        //final TextView t1 = (TextView) findViewById(R.id.textView);
        final FrameLayout f3 = (FrameLayout) findViewById(R.id.buttonframe3);
        final RelativeLayout f4 = (RelativeLayout) findViewById(R.id.Buttonframe4);


        final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.Buttonframe);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarcolordec);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);
        if (points < lowest | lowest == 0) lowest = score;
        if (points > highestcolor) highestcolor = score;


        scorecounter = scorecounter + score;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        updatelevel();
        calculatedistance();
        int x = points;
        Intent i = new Intent();


        buttonframe.setVisibility(View.INVISIBLE);

        g.setVisibility(View.VISIBLE);
        l3.setVisibility(View.INVISIBLE);
        f3.setVisibility(View.INVISIBLE);
        f4.setVisibility(View.INVISIBLE);
        editor.putInt("highestcolor", highestcolor);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }


    public void showZaire() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1);

        final FrameLayout t = (FrameLayout) findViewById(R.id.textfram);

        final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.Buttonframe);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbar2);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);
        if (points < lowest | lowest == 0) lowest = points;
        if (points > highest) highest = points;


        scorecounter = scorecounter + points;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }
        calculatedistance();
        updatelevel();
        int x = points;
        Intent i = new Intent();


        buttonframe.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("highestmath", highest);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }

    public void updatelevel() {

        TextView leveltext = (TextView) findViewById(R.id.textView81);
        TextView leveltext2 = (TextView) findViewById(R.id.textView8);
        TextView leveltext3 = (TextView) findViewById(R.id.textView82);
        Button leveltextsort = (Button) findViewById(R.id.textView82sort);
        TextView leveltext12 = (TextView) findViewById(R.id.textView8colorcat);
        Button leveltext22 = (Button) findViewById(R.id.textView8colordec);
        TextView leveltextpickcolor = (TextView) findViewById(R.id.textView8pickcolor);
        Button leveltextmemory = (Button) findViewById(R.id.textView8memory);
        Button leveltext2minesweeper = (Button) findViewById(R.id.textView8minesweeper);
        TextView leveltext2shootercat = (TextView) findViewById(R.id.textView8shootercat);

        TextView leveltext2cardcalc = (TextView) findViewById(R.id.textView8cardcalculation);

        Button leveltext2shootercircle = (Button) findViewById(R.id.textView8colorshooter);
        Button leveltext2pettrivia = (Button) findViewById(R.id.textView8pettrivia);

        Button mainlevel = (Button) findViewById(R.id.textView22);

        Button i1 = (Button) findViewById(R.id.imageView6);
        Button i2 = (Button) findViewById(R.id.imageView7);
        Button i3 = (Button) findViewById(R.id.imageView8);



        if (mathlevel == 0)

        {
            i1.setText(Integer.toString(mathlevel));
            i2.setText(Integer.toString(mathlevel + 1));
            i3.setText(Integer.toString(mathlevel + 2));

        }

       else if (mathlevel == 1)

        {
            i1.setText(Integer.toString(mathlevel));
            i2.setText(Integer.toString(mathlevel + 1));
            i3.setText(Integer.toString(mathlevel + 2));

        }

        else

        {
            i1.setText(Integer.toString(mathlevel -1 ));
            i2.setText(Integer.toString(mathlevel ));
            i3.setText(Integer.toString(mathlevel + 1));

        }



        mainlevel.setText(Integer.toString(mathlevel));
        leveltext2pettrivia.setText(Integer.toString(mathlevel));
        leveltext2cardcalc.setText(Integer.toString(mathlevel));
        leveltext2shootercircle.setText(Integer.toString(mathlevel));
        leveltext2shootercat.setText(Integer.toString(mathlevel));
        leveltextmemory.setText(Integer.toString(mathlevel));
        leveltextpickcolor.setText(Integer.toString(mathlevel));
        leveltext.setText(Integer.toString(mathlevel));
        leveltext2.setText(Integer.toString(mathlevel));
        leveltext3.setText(Integer.toString(mathlevel));
        leveltextsort.setText(Integer.toString(mathlevel));
        leveltext12.setText(Integer.toString(mathlevel));
        leveltext22.setText(Integer.toString(mathlevel));
        leveltext2minesweeper.setText(Integer.toString(mathlevel));

    }


    public void updategamecount()

    {
        shopbuttonupdate();
        TextView gamelifetext = (TextView) findViewById(R.id.textView121);
        TextView gamelifetext2 = (TextView) findViewById(R.id.textView12);
        Button gamelifetextcolordec = (Button) findViewById(R.id.textView12colordec);
        TextView gamelifetext3 = (TextView) findViewById(R.id.textView122);
        Button gamelifetextsort = (Button) findViewById(R.id.textView122sort);
        TextView gamelifetextcolorcat = (TextView) findViewById(R.id.textView12colorcat);
        TextView gamelifetextpickcolor = (TextView) findViewById(R.id.textView12pickcolor);
        Button gamelifetextmemory = (Button) findViewById(R.id.textView12memory);
        Button gamelifetextminesweeper = (Button) findViewById(R.id.textView12minesweeper);
        TextView gamelifetextshootercat = (TextView) findViewById(R.id.textView12shootercat);
        Button gamelifetextshootercircle = (Button) findViewById(R.id.textView12colorshooter);
        TextView gamelifetextcardcalculation = (TextView) findViewById(R.id.textView12cardcalculation);
        Button gamelifetextpettrivia = (Button) findViewById(R.id.textView12pettrivia);
        Button mainlife = (Button) findViewById(R.id.textView24);
        Button optionlife = (Button) findViewById(R.id.optionlifebutton);

        if (!purchaseinfiniteflag) {


            optionlife.setText(Integer.toString(gamecount));
            mainlife.setText(Integer.toString(gamecount));
            gamelifetextpettrivia.setText(Integer.toString(gamecount));
            gamelifetextcardcalculation.setText(Integer.toString(gamecount));
            gamelifetextshootercircle.setText(Integer.toString(gamecount));
            gamelifetextshootercat.setText(Integer.toString(gamecount));
            gamelifetextminesweeper.setText(Integer.toString(gamecount));
            gamelifetextmemory.setText(Integer.toString(gamecount));
            gamelifetextpickcolor.setText(Integer.toString(gamecount));
            gamelifetextcolorcat.setText(Integer.toString(gamecount));
            gamelifetextsort.setText(Integer.toString(gamecount));
            gamelifetext.setText(Integer.toString(gamecount));
            gamelifetext2.setText(Integer.toString(gamecount));
            gamelifetext3.setText(Integer.toString(gamecount));
            gamelifetextcolordec.setText(Integer.toString(gamecount));
        } else {

            gamelifetextsort.setText("");
            gamelifetextmemory.setText("");
            gamelifetextminesweeper.setText("");
            gamelifetextshootercircle.setText("");
            gamelifetextpettrivia.setText("");
            mainlife.setText("");
            optionlife.setText("");
            gamelifetextsort.setBackgroundResource(R.mipmap.heartinfinite);
            gamelifetextmemory.setBackgroundResource(R.mipmap.heartinfinite);
            gamelifetextminesweeper.setBackgroundResource(R.mipmap.heartinfinite);
            gamelifetextshootercircle.setBackgroundResource(R.mipmap.heartinfinite);
            gamelifetextpettrivia.setBackgroundResource(R.mipmap.heartinfinite);
            mainlife.setBackgroundResource(R.mipmap.heartinfinite);
            optionlife.setBackgroundResource(R.mipmap.heartinfinite);
        }
    }

    void updatecolorshooter()

    {
        TextView textt = (TextView) findViewById(R.id.textView10colorshooter);


        textt.setText(Integer.toString(score));


    }


    public void resetscore()

    {

        points = 0;
        score = 0;
        gamescore[0] = 0;
        tries = 0;
        level = 0;

        updatescorepettriva();
        updatescorecardcalculation();
        updatecolorshooter();
        updateScoresort();
        updateScore();
        updateScore2();
        updateScorecolordec();
        updateScorepickcolor();
        updateScorepickcolor();
        updateScorememory();
        updateminesweeper();
    }


    public void intro() {

        final RelativeLayout introlayout = (RelativeLayout) findViewById(R.id.introlayout);
        final RelativeLayout startlayout = (RelativeLayout) findViewById(R.id.startlayout);


        introlayout.setVisibility(View.VISIBLE);

        startlayout.setVisibility(View.INVISIBLE);

        new CountDownTimer(1000, 100) {
            public void onTick(long milsec) {
            }

            public void onFinish() {

                introlayout.setVisibility(View.INVISIBLE);

                startlayout.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public void activatecategories()

    {
        final Button b1 = (Button) findViewById(R.id.buttonsorti);
        final Button b2 = (Button) findViewById(R.id.buttonmemory);
        final Button b3 = (Button) findViewById(R.id.button16minesweeper);

        final Button b4 = (Button) findViewById(R.id.buttonshootercol);
        final Button b5 = (Button) findViewById(R.id.buttonshootercol2);
        final Button b6 = (Button) findViewById(R.id.buttonpettrvia);


        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);

        b4.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);



    }

    void alertbox()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        String warningtext = "Warning";
        String messagetext = "Player Id is empty";
        String oktext = "OK";

      /*  if (gamelang == ENGLISH) {
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }

        else if (gamelang == TURKISH) {
            warningtext = "UYARI";
            messagetext = "İnternet bağlantısı yok";
            oktext = "TAMAM";

        }
        else if (gamelang == FRENCH) {
            warningtext = "ATTENTION";
            messagetext = "Pas de connexion Internet";
            oktext = "D'accord";

        }
        else if (gamelang == SPANISH) {
            warningtext = "ADVERTENCIA";
            messagetext = "Sin conexión a Internet";
            oktext = "BUENO";

        }
        else if (gamelang == RUSSIAN) {
            warningtext = "ПРЕДУПРЕЖДЕНИЕ";
            messagetext = "Нет соединения с интернетом";
            oktext = "ХОРОШО";

        }
        else if (gamelang == CHINESE) {
            warningtext = "警告";
            messagetext = "沒有網絡連接";
            oktext = "好";

        }
        else if (gamelang == JAPANESE) {
            warningtext = "警告";
            messagetext = "インターネットに接続していない";
            oktext = "はい";

        }
        else if (gamelang == PORTUGUESE) {
            warningtext = "ATENÇÃO";
            messagetext = "Sem ligação à Internet";
            oktext = "OK";

        }
        else{
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }*/


        alertDialog.setTitle(warningtext);
        alertDialog.setMessage(messagetext);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, oktext,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    void alertboxemail()

    {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        String warningtext = "Warning";
        String messagetext = "Email is not Valid.";
        String oktext = "OK";

      /*  if (gamelang == ENGLISH) {
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }

        else if (gamelang == TURKISH) {
            warningtext = "UYARI";
            messagetext = "İnternet bağlantısı yok";
            oktext = "TAMAM";

        }
        else if (gamelang == FRENCH) {
            warningtext = "ATTENTION";
            messagetext = "Pas de connexion Internet";
            oktext = "D'accord";

        }
        else if (gamelang == SPANISH) {
            warningtext = "ADVERTENCIA";
            messagetext = "Sin conexión a Internet";
            oktext = "BUENO";

        }
        else if (gamelang == RUSSIAN) {
            warningtext = "ПРЕДУПРЕЖДЕНИЕ";
            messagetext = "Нет соединения с интернетом";
            oktext = "ХОРОШО";

        }
        else if (gamelang == CHINESE) {
            warningtext = "警告";
            messagetext = "沒有網絡連接";
            oktext = "好";

        }
        else if (gamelang == JAPANESE) {
            warningtext = "警告";
            messagetext = "インターネットに接続していない";
            oktext = "はい";

        }
        else if (gamelang == PORTUGUESE) {
            warningtext = "ATENÇÃO";
            messagetext = "Sem ligação à Internet";
            oktext = "OK";

        }
        else{
            warningtext = "Warning";
            messagetext = "No Internet Connection";
            oktext = "OK";

        }*/


        alertDialog.setTitle(warningtext);
        alertDialog.setMessage(messagetext);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, oktext,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public void deactivatecategories()

    {
        final Button b1 = (Button) findViewById(R.id.buttonsorti);
        final Button b2 = (Button) findViewById(R.id.buttonmemory);
        final Button b3 = (Button) findViewById(R.id.button16minesweeper);

        final Button b4 = (Button) findViewById(R.id.buttonshootercol);
        final Button b5 = (Button) findViewById(R.id.buttonshootercol2);
        final Button b6 = (Button) findViewById(R.id.buttonpettrvia);


        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);

        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);


    }

    public void start()

    {

        final ImageButton buttonOption1 = (ImageButton) findViewById(R.id.buttonMath1);
        final ImageButton buttonOption2 = (ImageButton) findViewById(R.id.buttonMath2);
        final ImageButton buttonOption3 = (ImageButton) findViewById(R.id.buttonMath3);
        final ImageButton buttonOption4 = (ImageButton) findViewById(R.id.buttonMath4);


        //  buttonOption1.setOnClickListener(this);

        buttonOption1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;

                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            tries++;
                            points = points + 50;


                            updateScore();
                        } else {
                            wrong++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();

                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption1.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption1.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption1.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        buttonOption2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            ;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            tries++;

                            points = points + 50;
                            updateScore();
                        } else {
                            wrong++;

                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption2.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption2.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption2.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        //  final Button retry = (Button) findViewById(R.id.retry);
        // retry.setVisibility(View.INVISIBLE);

        buttonOption3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }

                            tries++;
                            points = points + 50;


                            updateScore();
                        } else {
                            wrong++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption3.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption3.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption3.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        buttonOption4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption4.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath1:
                                if (operation == Operation.SUM) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath2:
                                if (operation == Operation.SUBSTRACT) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath3:
                                if (operation == Operation.MULTIPLICATION) {
                                    correct = true;
                                }
                                break;

                            case R.id.buttonMath4:
                                if (operation == Operation.DIVISION) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {

                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            points = points + 50;
                            updateScore();
                        } else {
                            wrong++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore();
                        }
                        //if(play) {
                        //createOperation();
                        //}
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption4.setAlpha(1f);
                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption4.setBackground(x);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption4.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 10) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation();
                                    }
                                }
                            }.start();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        //    final Button back = (Button) findViewById(R.id.Back);
  /*      retry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        retry.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        SharedPreferences pref;
                        pref = getSharedPreferences("info", MODE_PRIVATE);
                        final SharedPreferences.Editor editor = pref.edit();
                        gamestar--;
                        if(gamestar ==0) back();

                        editor.putInt("gamestar", gamestar);
                        editor.commit();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        retry.setAlpha(1f);
                        restartActivity();


                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });*/


    /*    back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        back.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        back.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });*/


        //buttonOption2.setOnClickListener(this);
        // buttonOption3.setOnClickListener(this);
        //buttonHome.setOnClickListener(this);

        createOperation();


    }


    public void vibrate(int duration) {

        if (vibration == true) {
            Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibs.vibrate(duration);
        }
    }

    private void createOperation() {


        final ImageButton buttonOption1 = (ImageButton) findViewById(R.id.buttonMath1);
        final ImageButton buttonOption2 = (ImageButton) findViewById(R.id.buttonMath2);
        final ImageButton buttonOption3 = (ImageButton) findViewById(R.id.buttonMath3);
        final ImageButton buttonOption4 = (ImageButton) findViewById(R.id.buttonMath4);


        Drawable x = getResources().getDrawable(R.drawable.buttonround);
        buttonOption1.setBackground(x);
        buttonOption2.setBackground(x);
        buttonOption3.setBackground(x);
        buttonOption4.setBackground(x);


        operation = generateRandOperation();

        //TextView op = (TextView) findViewById(R.id.textView42);


        Random r = new Random();
        Random r1 = new Random();
        int min = 2;
        int max = 10;
        int operand1 = r.nextInt((max - min) + 1) + min;
        int operand2 = r1.nextInt((max - min) + 1) + min;


        // int operand1 = NumberUtilities.generateRandNumber(2, 100);
        // int operand2 = NumberUtilities.generateRandNumber(2, 100);


        if (tries < 3)


        {
            min = 1;
            max = 6;

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number.add(i);
            Collections.shuffle(number);

            operand1 = number.get(0);
            operand2 = number.get(1);

            while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                Collections.shuffle(number);
                operand1 = number.get(0);
                operand2 = number.get(1);

            }
            // operand1 = r.nextInt((max - min) + 1) + min;
            //  operand2 = r1.nextInt((max - min) + 1) + min;
            operation = generateRandOperationsubadd();

        } else if (tries >= 3 & tries < 11)

        {

            min = 4;
            max = 11;

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number.add(i);
            Collections.shuffle(number);

            operand1 = number.get(0);
            operand2 = number.get(1);

            while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                Collections.shuffle(number);
                operand1 = number.get(0);
                operand2 = number.get(1);


            }


            operation = generateRandOperation();
            if (operation == Operation.DIVISION) {

                min = 2;
                max = 50;

                ArrayList<Integer> number2 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number2.add(i);
                Collections.shuffle(number2);

                operand1 = number2.get(0);
                operand2 = number2.get(1);

                while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                    Collections.shuffle(number2);
                    operand1 = number2.get(0);
                    operand2 = number2.get(1);

                }
            }

        } else {
            min = 7;
            max = 23;

            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number2.add(i);
            Collections.shuffle(number2);

            operand1 = number2.get(0);
            operand2 = number2.get(1);

            while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                Collections.shuffle(number2);
                operand1 = number2.get(0);
                operand2 = number2.get(1);

            }


            operation = generateRandOperation();
            if (operation == Operation.DIVISION) {

                min = 5;
                max = 100;

                ArrayList<Integer> number3 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number3.add(i);
                Collections.shuffle(number3);


                operand1 = number3.get(0);
                operand2 = number3.get(1);

                while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                    Collections.shuffle(number2);
                    operand1 = number2.get(0);
                    operand2 = number2.get(1);

                }
            }

            if (operation == Operation.MULTIPLICATION) {

                min = 5;
                max = 20;
                ArrayList<Integer> number3 = new ArrayList<Integer>();
                for (int i = min; i <= max; ++i) number3.add(i);
                Collections.shuffle(number3);

                operand1 = number3.get(0);
                operand2 = number3.get(1);

                while (operand1 == operand2 || (operand1 == 4 && operand2 == 2)) {
                    Collections.shuffle(number2);
                    operand1 = number2.get(0);
                    operand2 = number2.get(1);

                }

            }

        }

        int resultvalue = calculateRightValue(operation, operand1, operand2);


        while (operation == Operation.DIVISION & operand1 % operand2 != 0)

        {


            Random r11 = new Random();
            Random r12 = new Random();
            if (tries > 11) {

                min = 4;
                max = 100;
            }


            ArrayList<Integer> number3 = new ArrayList<Integer>();
            for (int i = min; i <= max; ++i) number3.add(i);
            Collections.shuffle(number3);

            operand1 = number3.get(0);
            operand2 = number3.get(1);


            while (operand1 == 4 && operand2 == 2) {
                Collections.shuffle(number3);
                operand1 = number3.get(0);
                operand2 = number3.get(1);

            }


            resultvalue = calculateRightValue(operation, operand1, operand2);


        }


        //rightValue= Double.valueOf(dtime.format(rightValue));
        //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));


        //resultvalue = (int)Math.round(resultvalue * 100) / (double) 100;

        if ((operation == Operation.DIVISION && operand1 % operand2 == 0) | operation == Operation.SUM | operation == Operation.SUBSTRACT | operation == Operation.MULTIPLICATION) {

            String operationText = String.valueOf(operand1);
            String operationText2 = String.valueOf(operand2) + " = " + resultvalue;

            TextView optext = (TextView) findViewById(R.id.textViewOperation);
            TextView optext2 = (TextView) findViewById(R.id.textView72);

            optext.setText(operationText);
            optext2.setText(operationText2);

            //TextView header = (TextView) findViewById(R.id.textView37);


            TextView question = (TextView) findViewById(R.id.textView45);
            TextView qintro = (TextView) findViewById(R.id.textView44);
            // qintro.setTypeface(tf);
            // qintro.setTextSize(30);

            // question.setTypeface(tf);
            // question.setTextSize(30);


            //DecimalFormat dtime = new DecimalFormat("#.##");
        }

        Random r2 = new Random();
        Random r3 = new Random();
        int min1 = 0;
        int max1 = 2;
        rightBox = r2.nextInt((max1 - min1) + 1) + min1;
        Operation randWrongValue1 = generateRandOperation();

        Operation randWrongValue2 = generateRandOperation();
        Operation randWrongValue3 = generateRandOperation();
        /*if(operation == Operation.SUM ) {

            randWrongValue1 = Operation.MULTIPLICATION;
            randWrongValue2 = Operation.DIVISION;
            randWrongValue3 = Operation.SUBSTRACT;
        }

        else if (operation == Operation.DIVISION )

        {

            randWrongValue1 = Operation.SUBSTRACT;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.MULTIPLICATION;

        }

        else if (operation == Operation.MULTIPLICATION )

        {

            randWrongValue1 = Operation.SUBSTRACT;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.DIVISION;

        }

        else if (operation == Operation.SUBSTRACT )

        {

            randWrongValue1 = Operation.MULTIPLICATION;
            randWrongValue2 = Operation.SUM;
            randWrongValue3 = Operation.DIVISION;

        }*/


        //rightBox = NumberUtilities.generateRandNumber(1, 3);
        //float randWrongValue1 = NumberUtilities.generateRandNumber(2);

/*
        switch (rightBox) {
            case 0:
                buttonOption1.setText(getOperationString(operation));
                buttonOption2.setText(getOperationString(randWrongValue1));
                buttonOption3.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;
            case 1:
                buttonOption2.setText(getOperationString(operation));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption3.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;
            case 2:
                buttonOption3.setText(getOperationString(operation));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption2.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(randWrongValue3));
                break;

            case 3:
                buttonOption3.setText(getOperationString(randWrongValue3));
                buttonOption1.setText(getOperationString(randWrongValue1));
                buttonOption2.setText(getOperationString(randWrongValue2));
                buttonOption4.setText(getOperationString(operation));
                break;


        }*/
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   /* public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/

    public enum Operation {
        SUM, DIVISION, MULTIPLICATION, SUBSTRACT
    }

    private Operation generateRandOperationsubadd() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 1;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch (rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.SUBSTRACT;
                break;

        }
        return operation;
    }


    private int calculateRightValue(Operation oper, int operand1, int operand2) {
        int calculation = 0;

        if (oper == Operation.SUM) {
            calculation = operand1 + operand2;
        } else if (oper == Operation.MULTIPLICATION) {
            calculation = operand1 * operand2;
        } else if (oper == Operation.SUBSTRACT) {
            calculation = operand1 - operand2;
        } else if (oper == Operation.DIVISION) {
            calculation = operand1 / operand2;
        }

        return calculation;
    }

    private String getOperationString(Operation oper) {
        String operationText = "";
        if (oper == Operation.SUM) {
            operationText = "+";
        } else if (oper == Operation.MULTIPLICATION) {
            operationText = "x";
        } else if (oper == Operation.SUBSTRACT) {
            operationText = "-";
        } else if (oper == Operation.DIVISION) {
            operationText = "/";
        }
        return operationText;
    }

    private Operation generateRandOperation() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 3;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch (rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.SUBSTRACT;
                break;
            case 2:
                operation = Operation.MULTIPLICATION;
                break;
            case 3:
                operation = Operation.DIVISION;
                break;
        }
        return operation;
    }


    @Override
    public void onClick(View view) {
        boolean correct = false;
        boolean play = true;

        switch (view.getId()) {
            case R.id.buttonMath1:
                if (operation == Operation.SUM) {
                    correct = true;
                }
                break;
            case R.id.buttonMath2:
                if (operation == Operation.DIVISION) {
                    correct = true;
                }
                break;
            case R.id.buttonMath3:
                if (operation == Operation.MULTIPLICATION) {
                    correct = true;
                }
                break;

            case R.id.buttonMath4:
                if (operation == Operation.SUBSTRACT) {
                    correct = true;
                }
                break;

            //case R.id.imageButtonHome:
            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
            //play = false;
            //break;
        }
        if (correct) {
            points = points + 50;
            correctnum++;
            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
            if (soundflag) {
                clickaudio.start();
                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }

                    ;
                });
            }


            updateScore();
        } else wrong++;

        if (play) {
            createOperation();
        }



    }


    private void updateminesweeper() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));


        if (score < 0) score = 0;

        TextView scoretext = (TextView) findViewById(R.id.textView10minesweeper);


        scoretext.setText(Integer.toString(score));


    }


    private void updateScorememory() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));


        if (gamescore[0] < 0) gamescore[0] = 0;
        final TextView scoretext = (TextView) findViewById(R.id.textView10memory);
        scoretext.setText(Integer.toString(gamescore[0]));


    }


    private void updateScorepickcolor() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));

        int pointsnew = points - (10 * wrong);

        if (pointsnew < 0) pointsnew = 0;
        final TextView scoretext = (TextView) findViewById(R.id.textView10pickcolor);
        scoretext.setText(Integer.toString(pointsnew));


    }


    private void updateScore() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));

        final TextView scoretext = (TextView) findViewById(R.id.textView101);
        scoretext.setText(Integer.toString(points));


    }

    private void updateScorecolordec() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));

        if (score < 0) score = 0;

        final TextView scoretext = (TextView) findViewById(R.id.textView10colordec);
        scoretext.setText(Integer.toString(score));


    }


    private void updateScoresort() {
        //TextView editText = (TextView) findViewById(R.id.score);
        //editText.setText(String.valueOf(points));
        //TextView textiq = (TextView) findViewById(R.id.textiq);
        //textiq.setText(Integer.toString(iq));
        if (points < 0) points = 0;


        final TextView scoretext = (TextView) findViewById(R.id.textView102sort);
        scoretext.setText(Integer.toString(points));


    }

    public void start2()

    {
        final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final Button buttonOption1 = (Button) findViewById(R.id.buttonMath122);
        final Button buttonOption2 = (Button) findViewById(R.id.buttonMath22);
        final Button buttonOption3 = (Button) findViewById(R.id.buttonMath32);
        final Button buttonOption4 = (Button) findViewById(R.id.buttonMath42);
        //ImageButton buttonHome = (ImageButton) findViewById(R.id.imageButtonHome);

        //  buttonOption1.setOnClickListener(this);

        buttonOption1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption1.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath122:
                                if (rightBox == 0) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath22:
                                if (rightBox == 1) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath32:
                                if (rightBox == 2) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath42:
                                if (rightBox == 3) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            points = points + 60;
                            tries++;


                            updateScore2();
                        } else {
                            wrong++;
                            tries++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore2();
                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption1.setAlpha(1f);


                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption1.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption1.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();
                        }

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        buttonOption2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption2.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath122:
                                if (rightBox == 0) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath22:
                                if (rightBox == 1) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath32:
                                if (rightBox == 2) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath42:
                                if (rightBox == 3) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            points = points + 60;
                            tries++;

                            updateScore2();
                        } else {
                            wrong++;
                            tries++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore2();
                        }
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption2.setAlpha(1f);


                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption2.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption2.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();
                        }


                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        buttonOption3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption3.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath122:
                                if (rightBox == 0) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath22:
                                if (rightBox == 1) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath32:
                                if (rightBox == 2) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath42:
                                if (rightBox == 3) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            points = points + 60;
                            tries++;
                            updateScore2();
                        } else {
                            wrong++;
                            tries++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore2();
                        }


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption3.setAlpha(1f);

                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption3.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption3.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();
                        }

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        buttonOption4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(0.85f);
                        v.setScaleY(0.85f);
                        buttonOption4.setAlpha(0.4f);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        boolean correct = false;
                        final boolean play = true;

                        switch (v.getId()) {
                            case R.id.buttonMath122:
                                if (rightBox == 0) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath22:
                                if (rightBox == 1) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath32:
                                if (rightBox == 2) {
                                    correct = true;
                                }
                                break;
                            case R.id.buttonMath42:
                                if (rightBox == 3) {
                                    correct = true;
                                }
                                break;
                            //case R.id.imageButtonHome:
                            //  startActivity(new Intent(MainActivity.this, MainActivity.class));
                            //play = false;
                            //break;
                        }
                        if (correct) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if (soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            points = points + 60;
                            tries++;
                            updateScore2();
                        } else {
                            wrong++;
                            tries++;
                            points = points - 10;
                            if (points <= 0) points = 0;
                            updateScore2();
                        }

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        buttonOption4.setAlpha(1f);

                        if (correct) {

                            Drawable x = getResources().getDrawable(R.drawable.operationtrue);
                            buttonOption4.setBackground(x);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();


                        } else {
                            Drawable x = getResources().getDrawable(R.drawable.operationfalse);
                            buttonOption4.setBackground(x);
                            vibrate(150);
                            new CountDownTimer(150, 1000) {
                                public void onTick(long milsec) {


                                }

                                public void onFinish() {
                                    if (play) {
                                        createOperation2();
                                    }
                                }
                            }.start();
                        }

                        break;
                    case MotionEvent.ACTION_CANCEL: {
                        ImageButton view = (ImageButton) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });


        createOperation2();

    }

    private void updateScore2() {

        final TextView scoretext = (TextView) findViewById(R.id.textView102);
        scoretext.setText(Integer.toString(points));
    }


    private void createOperation2() {
        Operation operation = generateRandOperation2();

        final Button buttonOption1 = (Button) findViewById(R.id.buttonMath122);
        final Button buttonOption2 = (Button) findViewById(R.id.buttonMath22);
        final Button buttonOption3 = (Button) findViewById(R.id.buttonMath32);
        final Button buttonOption4 = (Button) findViewById(R.id.buttonMath42);


        Drawable x = getResources().getDrawable(R.drawable.buttonround);
        buttonOption1.setBackground(x);
        buttonOption2.setBackground(x);
        buttonOption3.setBackground(x);
        buttonOption4.setBackground(x);

        int min = 0;
        int max = 100;
        if (tries < 2)

        {
            operation = generateRandOperationsubadd2();


            if (operation == Operation.SUM | operation == Operation.SUBSTRACT) {
                min = 0;
                max = 5;
            }

            Random r = new Random();
            Random r1 = new Random();
            // int operand1 = r.nextInt((max - min) + 1) + min;
            //int operand2 = r.nextInt((max - min) + 1) + min;

            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = 0; i <= 6; ++i) number2.add(i);
            Collections.shuffle(number2);

            int operand1 = number2.get(0);
            int operand2 = number2.get(1);


            // int operand1 = NumberUtilities.generateRandNumber(2, 100);
            // int operand2 = NumberUtilities.generateRandNumber(2, 100);

            int rightValue = calculateRightValue2(operation, operand1, operand2);

/*            while (operation == Operation.DIVISION & (operand1 % operand2 != 0))

            {

                operation = generateRandOperation();

                Random r2 = new Random();
                Random r3 = new Random();
                operand1 = r2.nextInt((max - min) + 1) + min;
                operand2 = r3.nextInt((max - min) + 1) + min;
                rightValue = calculateRightValue(operation, operand1, operand2);
            }
*/

            String operationText = String.valueOf(operand1) + " " +
                    getOperationString2(operation) + " " + String.valueOf(operand2) + " =";


            TextView textViewOperation = (TextView) findViewById(R.id.textViewOperation222);
            textViewOperation.setText(operationText);
            //DecimalFormat dtime = new DecimalFormat("#.##");


            //rightValue = (int)Math.round(rightValue * 100) / (double) 100;
            //rightValue= Double.valueOf(dtime.format(rightValue));
            //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));

            Random r2 = new Random();
            Random r3 = new Random();
            Random r4 = new Random();
            int min1 = 0;
            int max1 = 3;
            rightBox = r2.nextInt((max1 - min1) + 1) + min1;


            //rightBox = NumberUtilities.generateRandNumber(1, 3);
            //float randWrongValue1 = NumberUtilities.generateRandNumber(2);


            int[] array = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

            int[] array2 = {rightValue - 3, rightValue - 2, rightValue + -1, rightValue + 1, rightValue + 2, rightValue + 3};


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number.add(i);
            Collections.shuffle(number);

            int randWrongValue1;
            int randWrongValue2;
            int randWrongValue3;

            randWrongValue1 = array2[number.get(0)];
            randWrongValue2 = array2[number.get(1)];
            randWrongValue3 = array2[number.get(2)];


            switch (rightBox) {
                case 0:
                    buttonOption1.setText(Integer.toString(rightValue));
                    buttonOption2.setText(String.valueOf(randWrongValue1));
                    buttonOption4.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
                case 1:
                    buttonOption2.setText(Integer.toString(rightValue));
                    buttonOption4.setText(String.valueOf(randWrongValue1));
                    buttonOption3.setText(String.valueOf(randWrongValue2));
                    buttonOption1.setText(String.valueOf(randWrongValue3));
                    break;
                case 2:
                    buttonOption3.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption4.setText(String.valueOf(randWrongValue3));
                    break;
                case 3:
                    buttonOption4.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
            }
        } else if (tries >= 2 & tries < 5)

        {
            operation = generateRandOperationsubadd2();
            Operation operation2 = generateRandOperationsubadd2();

            if (operation == Operation.SUM | operation == Operation.SUBSTRACT) {
                min = 1;
                max = 10;
            }

            Random r = new Random();
            Random r1 = new Random();
            Random r2 = new Random();


            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = 1; i <= 10; ++i) number2.add(i);
            Collections.shuffle(number2);

            int operand1 = number2.get(0);
            int operand2 = number2.get(1);


            int rightValue = calculateRightValue2(operation, operand1, operand2);

            String operationText = String.valueOf(operand1) + " " +
                    getOperationString2(operation) + " " + String.valueOf(operand2) + " =";

            TextView textViewOperation = (TextView) findViewById(R.id.textViewOperation222);
            textViewOperation.setText(operationText);
            //DecimalFormat dtime = new DecimalFormat("#.##");

            int[] array = new int[200];

            int x1 = -10;
            for (int i = 0; i < 21; i++)

            {
                array[i] = x1 + i;

            }
            int[] array2 = {rightValue - 3, rightValue - 2, rightValue + -1, rightValue + 1, rightValue + 2, rightValue + 3};


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number.add(i);
            Collections.shuffle(number);

            int randWrongValue1;
            int randWrongValue2;
            int randWrongValue3;

            randWrongValue1 = array2[number.get(0)];
            randWrongValue2 = array2[number.get(1)];
            randWrongValue3 = array2[number.get(2)];

            Random r22 = new Random();
            Random r32 = new Random();
            Random r42 = new Random();
            int min1 = 0;
            int max1 = 3;
            rightBox = r2.nextInt((max1 - min1) + 1) + min1;


            switch (rightBox) {
                case 0:
                    buttonOption1.setText(Integer.toString(rightValue));
                    buttonOption2.setText(String.valueOf(randWrongValue1));
                    buttonOption4.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
                case 1:
                    buttonOption2.setText(Integer.toString(rightValue));
                    buttonOption4.setText(String.valueOf(randWrongValue1));
                    buttonOption3.setText(String.valueOf(randWrongValue2));
                    buttonOption1.setText(String.valueOf(randWrongValue3));
                    break;
                case 2:
                    buttonOption3.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption4.setText(String.valueOf(randWrongValue3));
                    break;
                case 3:
                    buttonOption4.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
            }
        } else if (tries >= 5 & tries < 9)

        {
            operation = generateRandOperationsubadd2();
            Operation operation2 = generateRandOperationsubadd2();

            min = 0;
            max = 5;


            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number2.add(i);
            Collections.shuffle(number2);

            int operand1 = number2.get(0);
            int operand2 = number2.get(1);
            int operand3 = number2.get(2);


            Random r = new Random();
            Random r1 = new Random();
            Random r2 = new Random();


            int rightValue = calculateRightValue2(operation2, calculateRightValue2(operation, operand1, operand2), operand3);


            String operationText = "(" + String.valueOf(operand1) + " " +
                    getOperationString2(operation) + " " + String.valueOf(operand2) + ") " + getOperationString2(operation2) + " " + String.valueOf(operand3) + " =";


            TextView textViewOperation = (TextView) findViewById(R.id.textViewOperation222);
            textViewOperation.setText(operationText);
            //DecimalFormat dtime = new DecimalFormat("#.##");


            int[] array = new int[200];

            int x1 = -25;
            for (int i = 0; i < 51; i++)

            {
                array[i] = x1 + i;


            }


            int[] array2 = {rightValue - 3, rightValue - 2, rightValue + -1, rightValue + 1, rightValue + 2, rightValue + 3};


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number.add(i);
            Collections.shuffle(number);

            int randWrongValue1;
            int randWrongValue2;
            int randWrongValue3;

            randWrongValue1 = array2[number.get(0)];
            randWrongValue2 = array2[number.get(1)];
            randWrongValue3 = array2[number.get(2)];

           /* if( rightValue==array2[number.get(0)]) randWrongValue1=array2[number.get(3)];
            else if (rightValue==array2[number.get(1)]) randWrongValue2=array2[number.get(3)];
            else if(rightValue==array2[number.get(2)]) randWrongValue3=array[number.get(3)];*/


            //rightValue = (int)Math.round(rightValue * 100) / (double) 100;
            //rightValue= Double.valueOf(dtime.format(rightValue));
            //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));
            Random r22 = new Random();
            Random r32 = new Random();
            Random r42 = new Random();
            int min1 = 0;
            int max1 = 3;
            rightBox = r2.nextInt((max1 - min1) + 1) + min1;


            switch (rightBox) {
                case 0:
                    buttonOption1.setText(Integer.toString(rightValue));
                    buttonOption2.setText(String.valueOf(randWrongValue1));
                    buttonOption4.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
                case 1:
                    buttonOption2.setText(Integer.toString(rightValue));
                    buttonOption4.setText(String.valueOf(randWrongValue1));
                    buttonOption3.setText(String.valueOf(randWrongValue2));
                    buttonOption1.setText(String.valueOf(randWrongValue3));
                    break;
                case 2:
                    buttonOption3.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption4.setText(String.valueOf(randWrongValue3));
                    break;
                case 3:
                    buttonOption4.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
            }
        } else if (tries >= 9 & tries < 12)

        {
            operation = generateRandOperationsubadd2();
            Operation operation2 = generateRandOperationsubadd2();

            if (operation == Operation.SUM | operation == Operation.SUBSTRACT) {
                min = 2;
                max = 10;
            }

            Random r = new Random();
            Random r1 = new Random();
            Random r2 = new Random();

            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = 2; i <= 10; ++i) number2.add(i);
            Collections.shuffle(number2);

            int operand1 = number2.get(0);
            int operand2 = number2.get(1);
            int operand3 = number2.get(2);



           /* int operand1 = r.nextInt((max - min) + 1) + min;
            int operand2 = r1.nextInt((max - min) + 1) + min;
            int operand3 = r2.nextInt((max - min) + 1) + min;*/


            int rightValue = calculateRightValue2(operation2, calculateRightValue2(operation, operand1, operand2), operand3);


            String operationText = "(" + String.valueOf(operand1) + " " +
                    getOperationString2(operation) + " " + String.valueOf(operand2) + ") " + getOperationString2(operation2) + " " + String.valueOf(operand3) + " =";


            TextView textViewOperation = (TextView) findViewById(R.id.textViewOperation222);
            textViewOperation.setText(operationText);
            //DecimalFormat dtime = new DecimalFormat("#.##");


            int[] array = new int[200];

            int x1 = -50;
            for (int i = 0; i < 101; i++)

            {
                array[i] = x1 + i;


            }

            int[] array2 = {rightValue - 3, rightValue - 2, rightValue + -1, rightValue + 1, rightValue + 2, rightValue + 3};


            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number.add(i);
            Collections.shuffle(number);

            int randWrongValue1;
            int randWrongValue2;
            int randWrongValue3;

            randWrongValue1 = array2[number.get(0)];
            randWrongValue2 = array2[number.get(1)];
            randWrongValue3 = array2[number.get(2)];


            //rightValue = (int)Math.round(rightValue * 100) / (double) 100;
            //rightValue= Double.valueOf(dtime.format(rightValue));
            //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));
            Random r22 = new Random();
            Random r32 = new Random();
            Random r42 = new Random();
            int min1 = 0;
            int max1 = 3;
            rightBox = r2.nextInt((max1 - min1) + 1) + min1;


            switch (rightBox) {
                case 0:
                    buttonOption1.setText(Integer.toString(rightValue));
                    buttonOption2.setText(String.valueOf(randWrongValue1));
                    buttonOption4.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
                case 1:
                    buttonOption2.setText(Integer.toString(rightValue));
                    buttonOption4.setText(String.valueOf(randWrongValue1));
                    buttonOption3.setText(String.valueOf(randWrongValue2));
                    buttonOption1.setText(String.valueOf(randWrongValue3));
                    break;
                case 2:
                    buttonOption3.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption4.setText(String.valueOf(randWrongValue3));
                    break;
                case 3:
                    buttonOption4.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
            }
        } else if (tries >= 12)

        {
            operation = generateRandOperationsubadd2();
            Operation operation2 = Operation.MULTIPLICATION;


            min = 2;
            max = 10;


            Random r = new Random();
            Random r1 = new Random();
            Random r2 = new Random();
           /* int operand1 = r.nextInt((max - min) + 1) + min;
            int operand2 = r1.nextInt((max - min) + 1) + min;
            int operand3 = r2.nextInt((max - min) + 1) + min;*/


            ArrayList<Integer> number2 = new ArrayList<Integer>();
            for (int i = 2; i <= 10; ++i) number2.add(i);
            Collections.shuffle(number2);

            int operand1 = number2.get(0);
            int operand2 = number2.get(1);
            int operand3 = number2.get(2);


            int rightValue = calculateRightValue2(operation2, calculateRightValue2(operation, operand1, operand2), operand3);


            String operationText = "(" + String.valueOf(operand1) + " " +
                    getOperationString2(operation) + " " + String.valueOf(operand2) + ") " + getOperationString2(operation2) + " " + String.valueOf(operand3) + " =";


            TextView textViewOperation = (TextView) findViewById(R.id.textViewOperation222);
            textViewOperation.setText(operationText);
            //DecimalFormat dtime = new DecimalFormat("#.##");


            int[] array = new int[500];

            int x1 = -100;
            for (int i = 0; i < 201; i++)

            {
                array[i] = x1 + i;

            }

            int[] array2 = {rightValue - 3, rightValue - 2, rightValue + -1, rightValue + 1, rightValue + 2, rightValue + 3};

            ArrayList<Integer> number = new ArrayList<Integer>();
            for (int i = 0; i <= 5; ++i) number.add(i);
            Collections.shuffle(number);

            int randWrongValue1;
            int randWrongValue2;
            int randWrongValue3;

            randWrongValue1 = array2[number.get(0)];
            randWrongValue2 = array2[number.get(1)];
            randWrongValue3 = array2[number.get(2)];

            //rightValue = (int)Math.round(rightValue * 100) / (double) 100;
            //rightValue= Double.valueOf(dtime.format(rightValue));
            //rightValue= Double.parseDouble(new DecimalFormat("##.##").format(rightValue));
            Random r22 = new Random();
            Random r32 = new Random();
            Random r42 = new Random();
            int min1 = 0;
            int max1 = 3;
            rightBox = r2.nextInt((max1 - min1) + 1) + min1;


            switch (rightBox) {
                case 0:
                    buttonOption1.setText(Integer.toString(rightValue));
                    buttonOption2.setText(String.valueOf(randWrongValue1));
                    buttonOption4.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
                case 1:
                    buttonOption2.setText(Integer.toString(rightValue));
                    buttonOption4.setText(String.valueOf(randWrongValue1));
                    buttonOption3.setText(String.valueOf(randWrongValue2));
                    buttonOption1.setText(String.valueOf(randWrongValue3));
                    break;
                case 2:
                    buttonOption3.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption4.setText(String.valueOf(randWrongValue3));
                    break;
                case 3:
                    buttonOption4.setText(Integer.toString(rightValue));
                    buttonOption1.setText(String.valueOf(randWrongValue1));
                    buttonOption2.setText(String.valueOf(randWrongValue2));
                    buttonOption3.setText(String.valueOf(randWrongValue3));
                    break;
            }
        }


    }

    private Integer calculateRightValue2(Operation oper, int operand1, int operand2) {
        int calculation = 0;

        if (oper == Operation.SUM) {
            calculation = operand1 + operand2;
        } else if (oper == Operation.MULTIPLICATION) {
            calculation = operand1 * operand2;
        } else if (oper == Operation.SUBSTRACT) {
            calculation = operand1 - operand2;
        } else if (oper == Operation.DIVISION) {
            calculation = operand1 / operand2;
        }

        return calculation;
    }

    private String getOperationString2(Operation oper) {
        String operationText = "";
        if (oper == Operation.SUM) {
            operationText = "+";
        } else if (oper == Operation.MULTIPLICATION) {
            operationText = "*";
        } else if (oper == Operation.SUBSTRACT) {
            operationText = "-";
        } else if (oper == Operation.DIVISION) {
            operationText = "/";
        }
        return operationText;
    }

    private Operation generateRandOperation2() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 3;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch (rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.DIVISION;
                break;
            case 2:
                operation = Operation.MULTIPLICATION;
                break;
            case 3:
                operation = Operation.SUBSTRACT;
                break;
        }
        return operation;
    }


    private Operation generateRandOperationsubadd2() {

        Random r4 = new Random();
        int min1 = 0;
        int max1 = 1;
        int rand = r4.nextInt((max1 - min1) + 1) + min1;


        //int rand = NumberUtilities.generateRandNumber(1, 4);


        Operation operation = null;

        switch (rand) {
            case 0:
                operation = Operation.SUM;
                break;
            case 1:
                operation = Operation.SUBSTRACT;
                break;

        }
        return operation;
    }


    public void showZairememory() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid1memory);

        final TableLayout t = (TableLayout) findViewById(R.id.TableLayout03);


        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarmemory);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);

        if (gamescore[0] < lowest | lowest == 0) lowest = gamescore[0];
        if (gamescore[0] > highest) highest = gamescore[0];
        scorecounter = scorecounter + gamescore[0];


        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        updatelevel();

        calculatedistance();


        t.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("highestmath", highest);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }


    public void showZaire2() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid12);

        final FrameLayout t = (FrameLayout) findViewById(R.id.textfram222);

        final RelativeLayout buttonframe = (RelativeLayout) findViewById(R.id.buttonframe);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarsimplicity);


        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);
        if (points < lowest | lowest == 0) lowest = points;
        if (points > highest) highest = points;


        scorecounter = scorecounter + points;

        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int) (scorecounter * 100 / LEVELMAX)) + "%");


        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int) ((scorecounter - LEVELMAX) * 100 / LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter - LEVELMAX;

        }

        updatelevel();
        calculatedistance();


        buttonframe.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        g.setVisibility(View.VISIBLE);


        editor.putInt("highestmath", highest);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }


    int min(int x, int y)

    {

        if (x < y) return x;
        else return y;
    }

    int max(int x, int y)

    {

        if (x > y) return x;
        else return y;
    }


    int ninemin(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9)


    {
        return min(x1, min(x2, min(x3, min(x4, min(x5, min(x6, min(x7, min(x8, x9))))))));

    }

    int eightmin(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8)


    {
        return min(x1, min(x2, min(x3, min(x4, min(x5, min(x6, min(x7, x8)))))));

    }


    int sevenmin(int x1, int x2, int x3, int x4, int x5, int x6, int x7)


    {
        return min(x1, min(x2, min(x3, min(x4, min(x5, min(x6, x7))))));

    }


    int sixmin(int x1, int x2, int x3, int x4, int x5, int x6)


    {
        return min(x1, min(x2, min(x3, min(x4, min(x5, x6)))));

    }


    int sixmax(int x1, int x2, int x3, int x4, int x5, int x6) {

        return max(x1, max(x2, max(x3, max(x4, max(x5, x6)))));

    }


    int fivemin(int x1, int x2, int x3, int x4, int x5)


    {
        return min(x1, min(x2, min(x3, min(x4, x5))));

    }


    int fourmin(int x1, int x2, int x3, int x4)


    {
        return min(x1, min(x2, min(x3, x4)));

    }

    int threemin(int x1, int x2, int x3)


    {
        return min(x1, min(x2, x3));

    }


    void listnine(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9) {


        array9[0] = x1;
        array9[1] = x2;
        array9[2] = x3;
        array9[3] = x4;
        array9[4] = x5;
        array9[5] = x6;
        array9[6] = x7;
        array9[7] = x8;
        array9[8] = x9;

        for (int i = 1; i < 9; i++) {
            if (array9[i] == ninemin(array9[0], array9[1], array9[2], array9[3], array9[4], array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[0];
                array9[0] = y1;

            }

        }


        for (int i = 2; i < 9; i++) {
            if (array9[i] == eightmin(array9[1], array9[2], array9[3], array9[4], array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[1];
                array9[1] = y1;

            }

        }

        for (int i = 3; i < 9; i++) {
            if (array9[i] == sevenmin(array9[2], array9[3], array9[4], array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[2];
                array9[2] = y1;

            }

        }


        for (int i = 4; i < 9; i++) {
            if (array9[i] == sixmin(array9[3], array9[4], array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[3];
                array9[3] = y1;

            }

        }


        for (int i = 5; i < 9; i++) {
            if (array9[i] == fivemin(array9[4], array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[4];
                array9[4] = y1;

            }

        }


        for (int i = 6; i < 9; i++) {
            if (array9[i] == fourmin(array9[5], array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[5];
                array9[5] = y1;

            }

        }


        for (int i = 7; i < 9; i++) {
            if (array9[i] == threemin(array9[6], array9[7], array9[8])) {
                int y1 = array9[i];

                array9[i] = array9[6];
                array9[6] = y1;

            }

        }


        if (array9[7] > array9[8]) {

            int y1 = array9[8];
            array9[8] = array9[7];
            array9[7] = y1;

        }



/*
        b1.setText(Integer.toString( array9[0]));
        b2.setText(Integer.toString(array9[1]));
        b3.setText(Integer.toString(array9[2]));
        b4.setText(Integer.toString(array9[3]));
        b5.setText(Integer.toString(array9[4]));
        b6.setText(Integer.toString(array9[5]));
        b7.setText(Integer.toString(array9[6]));
        b8.setText(Integer.toString(array9[7]));
        b9.setText(Integer.toString(array9[8]));*/


    }


    void listthree(int x1, int x2, int x3)

    {

        array3[0] = x1;
        array3[1] = x2;
        array3[2] = x3;

        for (int i = 1; i < 3; i++) {
            if (array3[i] == threemin(array3[0], array3[1], array3[2])) {
                int y1 = array3[i];

                array3[i] = array3[0];
                array3[0] = y1;
            }

        }


        if (array3[1] > array3[2]) {

            int y1 = array3[2];
            array3[2] = array3[1];
            array3[1] = y1;

        }


    }


    void listsix(int x1, int x2, int x3, int x4, int x5, int x6) {


        array[0] = x1;
        array[1] = x2;
        array[2] = x3;
        array[3] = x4;
        array[4] = x5;
        array[5] = x6;


        for (int i = 1; i < 6; i++) {
            if (array[i] == sixmin(array[0], array[1], array[2], array[3], array[4], array[5])) {
                int y1 = array[i];

                array[i] = array[0];
                array[0] = y1;

            }

        }

        for (int i = 2; i < 6; i++) {
            if (array[i] == fivemin(array[1], array[2], array[3], array[4], array[5])) {
                int y1 = array[i];

                array[i] = array[1];
                array[1] = y1;
            }

        }


        for (int i = 3; i < 6; i++) {
            if (array[i] == fourmin(array[2], array[3], array[4], array[5])) {
                int y1 = array[i];

                array[i] = array[2];
                array[2] = y1;
            }

        }

        for (int i = 4; i < 6; i++) {
            if (array[i] == threemin(array[3], array[4], array[5])) {
                int y1 = array[i];

                array[i] = array[3];
                array[3] = y1;
            }

        }


        if (array[4] > array[5]) {

            int y1 = array[5];
            array[5] = array[4];
            array[4] = y1;

        }


      /*  b1.setText(Integer.toString( array[0]));
        b2.setText(Integer.toString(array[1]));
        b3.setText(Integer.toString(array[2]));
        b4.setText(Integer.toString(array[3]));
        b5.setText(Integer.toString(array[4]));
        b6.setText(Integer.toString(array[5]));
        b7.setText(Integer.toString(array[5]));
        b8.setText(Integer.toString(array[5]));
        b9.setText(Integer.toString(array[5]));*/

    }


    public void sortinglevelmanager() {


        switch(mathlevel)

    {

        case 0:
            SORTING3BUTTONLEVEL = 15;
            SORTING6BUTTONLEVEL = 100;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 20;
            SORTINGMAX6 = 50;

            SORTINGMIN9 = 10;
            SORTINGMAX9 = 100;
            return;

        case 1:
             SORTING3BUTTONLEVEL = 10;
             SORTING6BUTTONLEVEL = 100;
             SORTINGMIN3 = 1;
             SORTINGMAX3 = 10;

             SORTINGMIN6 = 20;
             SORTINGMAX6 = 50;

             SORTINGMIN9 = 1;
             SORTINGMAX9 = 100;
            return;

        case 2:
             SORTING3BUTTONLEVEL = 8;
             SORTING6BUTTONLEVEL = 100;
             SORTINGMIN3 = 1;
             SORTINGMAX3 = 10;

             SORTINGMIN6 = 20;
             SORTINGMAX6 = 50;

             SORTINGMIN9 = 1;
             SORTINGMAX9 = 100;
            return;
        case 3:
            SORTING3BUTTONLEVEL = 7;
            SORTING6BUTTONLEVEL = 100;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 10;
            SORTINGMAX6 = 50;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 4:
            SORTING3BUTTONLEVEL = 6;
            SORTING6BUTTONLEVEL = 10;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 10;
            SORTINGMAX6 = 50;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 5:
            SORTING3BUTTONLEVEL = 5;
            SORTING6BUTTONLEVEL = 9;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 10;
            SORTINGMAX6 = 50;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 6:
            SORTING3BUTTONLEVEL = 4;
            SORTING6BUTTONLEVEL = 8;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 1;
            SORTINGMAX6 = 50;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 7:
            SORTING3BUTTONLEVEL = 5;
            SORTING6BUTTONLEVEL = 9;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 1;
            SORTINGMAX6 = 80;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 8:
            SORTING3BUTTONLEVEL = 4;
            SORTING6BUTTONLEVEL = 8;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 1;
            SORTINGMAX6 = 80;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        case 9:
            SORTING3BUTTONLEVEL = 4;
            SORTING6BUTTONLEVEL = 7;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;

            SORTINGMIN6 = 1;
            SORTINGMAX6 = 80;

            SORTINGMIN9 = 1;
            SORTINGMAX9 = 100;
            return;
        }

        if (mathlevel >=10 && mathlevel< 15){


            SORTING3BUTTONLEVEL = 3;
            SORTING6BUTTONLEVEL = 5;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 10;
            SORTINGMIN6 = 1;
            SORTINGMAX6 = 80;

            SORTINGMIN9 = 30;
            SORTINGMAX9 = 300;

        }

      else  if (mathlevel >=15 && mathlevel< 25){

            SORTING3BUTTONLEVEL = 3;
            SORTING6BUTTONLEVEL = 3;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 30;
            SORTINGMIN6 = 1;
            SORTINGMAX6 = 100;

            SORTINGMIN9 = 100;
            SORTINGMAX9 = 300;
        }


        else  if (mathlevel >=25 && mathlevel< 40){

            SORTING3BUTTONLEVEL = 2;
            SORTING6BUTTONLEVEL = 2;
            SORTINGMIN3 = 1;
            SORTINGMAX3 = 40;
            SORTINGMIN6 = 1;
            SORTINGMAX6 = 100;

            SORTINGMIN9 = 100;
            SORTINGMAX9 = 300;
        }

        else  if (mathlevel >=40 && mathlevel< 70){

            SORTING3BUTTONLEVEL = 1;
            SORTING6BUTTONLEVEL = 4;
            SORTINGMIN3 = 10;
            SORTINGMAX3 = 40;
            SORTINGMIN6 = 1;
            SORTINGMAX6 = 200;

            SORTINGMIN9 = 100;
            SORTINGMAX9 = 500;
        }

        else  if (mathlevel >=70){

            SORTING3BUTTONLEVEL = 1;
            SORTING6BUTTONLEVEL = 2;
            SORTINGMIN3 = 10;
            SORTINGMAX3 = 100;
            SORTINGMIN6 = 100;
            SORTINGMAX6 = 200;
            SORTINGMIN9 = 100;
            SORTINGMAX9 = 500;
        }

    }

    public void start3()
    {

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);
        Random r = new Random();
        int min = 0;
        int max = 1;
        int choice = r.nextInt((max - min) + 1) + min;

        if(choice<1) ascending=true;


        else  ascending =false;
        TextView introtextsort = (TextView) findViewById(R.id.textView18);

        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.INVISIBLE);
        b5.setVisibility(View.INVISIBLE);
        b6.setVisibility(View.INVISIBLE);
        b7.setVisibility(View.INVISIBLE);
        b8.setVisibility(View.INVISIBLE);
        b9.setVisibility(View.INVISIBLE);
        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = SORTINGMIN3; i <= SORTINGMAX3; ++i) number.add(i);
        Collections.shuffle(number);


        int  min2 = 1;
        int max2 = 10;


        Random rr = new Random();


        ArrayList<Integer> number2 = new ArrayList<Integer>();
        for (int i = SORTINGMIN3; i < SORTINGMAX3; ++i) number2.add(i);
        Collections.shuffle(number2);



        x1 = number2.get(0);
        x2 =   number2.get(1);
       x3=  number2.get(2);

      //  x1= number.get(pick1);
       // x2=number.get(pick2);
       // x3= number.get(pick3);


        b1.setText(Integer.toString(x1));
        b2.setText(Integer.toString(x2));
        b3.setText(Integer.toString(x3));


        if (ascending==true){

            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));



        }

        else
        {
            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));





        }



        listthree(x1, x2, x3);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x1 == array3[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();


                    } else if (counter == 1 & x1 == array3[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    }  else if (counter == 2 & x1 == array3[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            // // points=points+30;

                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        b1.startAnimation(shake);
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        vibrate(150);

                    }
                } else

                {
                    if (counter == 0 & x1 == array3[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 1 & x1 == array3[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    }  else if (counter == 2 & x1 == array3[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            //  // points=points+30;
                            //  final TextView scoretext = (TextView) findViewById(R.id.score);
                            // scoretext.setText(Integer.toString(points));
                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        b1.startAnimation(shake);
                        vibrate(150);
                        /*x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));


                        }*/


                    }

                }


            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x2 == array3[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();


                    } else if (counter == 1 & x2 == array3[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    }  else if (counter == 2 & x2 == array3[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            // // points=points+30;

                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        b2.startAnimation(shake);
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        vibrate(150);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/
                    }
                } else

                {
                    if (counter == 0 & x2 == array3[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 1 & x2 == array3[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    }  else if (counter == 2 & x2 == array3[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            // // points=points+30;
                            //final TextView scoretext = (TextView) findViewById(R.id.score);
                            //scoretext.setText(Integer.toString(points));
                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        b2.startAnimation(shake);
                        vibrate(150);
                        /*x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));


                        }*/


                    }

                }


            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x3 == array3[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();


                    } else if (counter == 1 & x3 == array3[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    }  else if (counter == 2 & x3 == array3[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            //// points=points+30;

                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        b3.startAnimation(shake);
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        vibrate(150);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/
                    }
                } else

                {
                    if (counter == 0 & x3 == array3[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 1 & x3 == array3[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    }  else if (counter == 2 & x3 == array3[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 3) {
                            counter = 0;
                            level++;
                            //// points=points+30;

                            if (level >= SORTING3BUTTONLEVEL) start6();
                            else
                                start3();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        b3.startAnimation(shake);
                        vibrate(150);
                        /*x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));


                        }*/


                    }

                }


            }

        });





    }



    public void start9()

    {

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);
        Random r = new Random();
        int min = 0;
        int max = 1;
        int choice = r.nextInt((max - min) + 1) + min;

        if(choice<1) ascending=true;
        else  ascending =false;
        TextView introtextsort = (TextView) findViewById(R.id.textView18);

        if (ascending) introtextsort.setText(getResources().getString(R.string.sorting_directions_ascending2));
        else introtextsort.setText(getResources().getString(R.string.sorting_directions_descending2));

        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        b7.setVisibility(View.VISIBLE);
        b8.setVisibility(View.VISIBLE);
        b9.setVisibility(View.VISIBLE);

        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = SORTINGMIN9; i <= SORTINGMAX9; ++i) number.add(i);
        Collections.shuffle(number);

        x1= number.get(0);
        x2=number.get(1);
        x3= number.get(2);
        x4=number.get(3);
        x5= number.get(4);
        x6=number.get(5);
        x7=number.get(6);
        x8=number.get(7);
        x9=number.get(8);


        b1.setText(Integer.toString(x1));
        b2.setText(Integer.toString(x2));
        b3.setText(Integer.toString(x3));
        b4.setText(Integer.toString(x4));
        b5.setText(Integer.toString(x5));
        b6.setText(Integer.toString(x6));
        b7.setText(Integer.toString(x7));
        b8.setText(Integer.toString(x8));
        b9.setText(Integer.toString(x9));

        if (ascending==true){

            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

        }

        else
        {
            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

        }



        listnine(x1, x2, x3, x4, x5, x6, x7, x8, x9);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x1 == array9[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                        updateScoresort();
                    } else if (counter == 1 & x1 == array9[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 2 & x1 == array9[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 3 & x1 == array9[3]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 4 & x1 == array9[4]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();


                    } else if (counter == 5 & x1 == array9[5]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 6 & x1 == array9[6]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 7 & x1 == array9[7]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 8 & x1 == array9[8]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            // points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        vibrate(150);
                        b1.startAnimation(shake);
                    /*    x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x2, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x1 == array9[8]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 1 & x1 == array9[7]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 2 & x1 == array9[6]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 3 & x1 == array9[5]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 4 & x1 == array9[4]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 5 & x1 == array9[3]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 6 & x1 == array9[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                    } else if (counter == 7 & x1 == array9[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 8 & x1 == array9[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;
                            updateScoresort();
                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        updateScoresort();
                        vibrate(150);
                        b1.startAnimation(shake);
                     /*   x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x2, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x2 == array9[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        updateScoresort();

                    } else if (counter == 1 & x2 == array9[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x2 == array9[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x2 == array9[3]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x2 == array9[4]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x2 == array9[5]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x2 == array9[6]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x2 == array9[7]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x2 == array9[8]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b2.startAnimation(shake);
                     /*   x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x2, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x2 == array9[8]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x2 == array9[7]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x2 == array9[6]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x2 == array9[5]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x2 == array9[4]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x2 == array9[3]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x2 == array9[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x2 == array9[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x2 == array9[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b2.startAnimation(shake);
                        /*
                        x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x2, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x3 == array9[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x3 == array9[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        points=points+30;

                    } else if (counter == 2 & x3 == array9[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x3 == array9[3]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x3 == array9[4]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x3 == array9[5]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x3 == array9[6]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x3 == array9[7]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x3 == array9[8]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b3.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x3 == array9[8]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x3 == array9[7]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x3 == array9[6]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x3 == array9[5]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x3 == array9[4]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        points=points+30;

                    } else if (counter == 5 & x3 == array9[3]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x3 == array9[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x3 == array9[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x3 == array9[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b3.startAnimation(shake);
                    /*    x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x4 == array9[0]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x4 == array9[1]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x4 == array9[2]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x4 == array9[3]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x4 == array9[4]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x4 == array9[5]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x4 == array9[6]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x4 == array9[7]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x4 == array9[8]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b4.startAnimation(shake);
                   /*     x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x4 == array9[8]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x4 == array9[7]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x4 == array9[6]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;
                        points=points+30;

                    } else if (counter == 3 & x4 == array9[5]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x4 == array9[4]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x4 == array9[3]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x4 == array9[2]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x4 == array9[1]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x4 == array9[0]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b4.startAnimation(shake);
                     /*   x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x5 == array9[0]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x5 == array9[1]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x5 == array9[2]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x5 == array9[3]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x5 == array9[4]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x5 == array9[5]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x5 == array9[6]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x5 == array9[7]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x5 == array9[8]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b5.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x5 == array9[8]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x5 == array9[7]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x5 == array9[6]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x5 == array9[5]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x5 == array9[4]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x5 == array9[3]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x5 == array9[2]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x5 == array9[1]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x5 == array9[0]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b5.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x6 == array9[0]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x6 == array9[1]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x6 == array9[2]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x6 == array9[3]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x6 == array9[4]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x6 == array9[5]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x6 == array9[6]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x6 == array9[7]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x6 == array9[8]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b6.startAnimation(shake);
                     /*   x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x6 == array9[8]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x6 == array9[7]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x6 == array9[6]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x6 == array9[5]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x6 == array9[4]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x6 == array9[3]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x6 == array9[2]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x6 == array9[1]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x6 == array9[0]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b6.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x7 == array9[0]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x7 == array9[1]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x7 == array9[2]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x7 == array9[3]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x7 == array9[4]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x7 == array9[5]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x7 == array9[6]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x7 == array9[7]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x7 == array9[8]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b7.startAnimation(shake);
                    /*    x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x7 == array9[8]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x7 == array9[7]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x7 == array9[6]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x7 == array9[5]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x7 == array9[4]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x7 == array9[3]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x7 == array9[2]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x7 == array9[1]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x7 == array9[0]) {
                        b7.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //   counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b7.startAnimation(shake);
                        /*
                        x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });


        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x8 == array9[0]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x8 == array9[1]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x8 == array9[2]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x8 == array9[3]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x8 == array9[4]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x8 == array9[5]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x8 == array9[6]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x8 == array9[7]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x8 == array9[8]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b8.startAnimation(shake);
                     /*   x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x8 == array9[8]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x8 == array9[7]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x8 == array9[6]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x8 == array9[5]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x8 == array9[4]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x8 == array9[3]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x8 == array9[2]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x8 == array9[1]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 8 & x8 == array9[0]) {
                        b8.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b8.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });


        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x9 == array9[0]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x9 == array9[1]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x9 == array9[2]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x9 == array9[3]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x9 == array9[4]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x9 == array9[5]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x9 == array9[6]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x9 == array9[7]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;



                    } else if (counter == 8 & x9 == array9[8]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;

                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b9.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                } else

                {
                    if (counter == 0 & x9 == array9[8]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x9 == array9[7]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x9 == array9[6]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x9 == array9[5]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x9 == array9[4]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x9 == array9[3]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 6 & x9 == array9[2]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                    } else if (counter == 7 & x9 == array9[1]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;



                    } else if (counter == 8 & x9 == array9[0]) {
                        b9.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 9) {
                            counter = 0;
                            level++;
                            points = points + 30;


                            start9();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b9.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
                        x7 = number.get(6);
                        x8 = number.get(7);
                        x9 = number.get(8);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        b7.setText(Integer.toString(x7));
                        b8.setText(Integer.toString(x8));
                        b9.setText(Integer.toString(x9));

                        listnine(x1, x3, x3, x4, x5, x6, x7, x8, x9);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);
                        b7.setVisibility(View.VISIBLE);
                        b8.setVisibility(View.VISIBLE);
                        b9.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                           b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentascending));

                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b7.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b8.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b9.setBackground(getResources().getDrawable(R.drawable.segmentdescending));

                        }*/
                    }
                }


            }

        });

    }


    public void start6()
    {

        final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(200);
        Random r = new Random();
        int min = 0;
        int max = 1;
        int choice = r.nextInt((max - min) + 1) + min;

        if(choice<1) ascending=true;
        else  ascending =false;
        TextView introtextsort = (TextView) findViewById(R.id.textView18);

        if (ascending) introtextsort.setText(getResources().getString(R.string.sorting_directions_ascending2));
        else introtextsort.setText(getResources().getString(R.string.sorting_directions_descending2));
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        b5.setVisibility(View.VISIBLE);
        b6.setVisibility(View.VISIBLE);
        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = SORTINGMIN6; i <= SORTINGMAX6; ++i) number.add(i);
        Collections.shuffle(number);



        ArrayList<Integer> number2 = new ArrayList<Integer>();
        for (int i = SORTINGMIN6; i < SORTINGMAX6; ++i) number2.add(i);
        Collections.shuffle(number2);
        Collections.shuffle(number2);
        Collections.shuffle(number2);


        x1 = number2.get(1);
        x2 =   number2.get(2);
        x3 =  number2.get(3);
        x4 = number2.get(4);
        x5 =   number2.get(5);
        x6 =  number2.get(6);


      /*  x1= number.get(pick1);
        x2=number.get(pick2);
        x3= number.get(pick3);
        x4=number.get(pick4);
        x5= number.get(pick5);
        x6=number.get(pick6);*/

        b1.setText(Integer.toString(x1));
        b2.setText(Integer.toString(x2));
        b3.setText(Integer.toString(x3));
        b4.setText(Integer.toString(x4));
        b5.setText(Integer.toString(x5));
        b6.setText(Integer.toString(x6));

        if (ascending==true){

            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));


            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


        }

        else
        {
            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




        }



        listsix(x1, x2, x3, x4, x5, x6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x1 == array[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;



                    } else if (counter == 1 & x1 == array[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x1 == array[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x1 == array[3]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x1 == array[4]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x1 == array[5]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;
                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        b1.startAnimation(shake);
                        wrong++;
                        points=points-10;
                        vibrate(150);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/
                    }
                } else

                {
                    if (counter == 0 & x1 == array[5]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x1 == array[4]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x1 == array[3]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x1 == array[2]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x1 == array[1]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x1 == array[0]) {
                        b1.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        b1.startAnimation(shake);
                        vibrate(150);
                        /*x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if (choice < 1) ascending = true;
                        else ascending = false;


                        if (ascending == true) {

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));;
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        } else {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));


                        }*/


                    }

                }


            }

        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x2 == array[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x2 == array[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x2 == array[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x2 == array[3]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x2 == array[4]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x2 == array[5]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b2.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
/*
                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/
                    }
                }
                else

                {
                    if (counter == 0 & x2 == array[5]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x2 == array[4]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x2 == array[3]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x2 == array[2]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x2 == array[1]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x2 == array[0]) {
                        b2.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b2.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/


                    }

                }


            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x3 == array[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x3 == array[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x3 == array[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x3 == array[3]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x3 == array[4]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x3 == array[5]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b3.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
/*
                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/
                    }
                }
                else

                {
                    if (counter == 0 & x3 == array[5]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x3 == array[4]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x3 == array[3]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x3 == array[2]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x3 == array[1]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x3 == array[0]) {
                        b3.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //  counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b3.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);
/*
                        b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/


                    }

                }


            }

        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x4 == array[0]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x4 == array[1]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x4 == array[2]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x4 == array[3]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x4 == array[4]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x4 == array[5]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b4.startAnimation(shake);
                    /*    x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);


                       /* b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/
                    }
                }
                else

                {
                    if (counter == 0 & x4 == array[5]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x4 == array[4]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x4 == array[3]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x4 == array[2]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x4 == array[1]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x4 == array[0]) {
                        b4.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b4.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);


                        /*b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/


                    }

                }


            }

        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x5 == array[0]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x5 == array[1]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x5 == array[2]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x5 == array[3]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x5 == array[4]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x5 == array[5]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b5.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);


                       /* b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/
                    }
                }
                else

                {
                    if (counter == 0 & x5 == array[5]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x5 == array[4]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x5 == array[3]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x5 == array[2]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x5 == array[1]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x5 == array[0]) {
                        b5.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        // counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b5.startAnimation(shake);
                      /*  x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                      /*  b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/


                    }

                }


            }

        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ascending == true) {

                    if (counter == 0 & x6 == array[0]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x6 == array[1]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x6 == array[2]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x6 == array[3]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x6 == array[4]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 5 & x6 == array[5]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            // // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //counter = 0;
                        wrong++;
                        points=points-10;

                        vibrate(150);
                        b6.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                       /* b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        { b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));




                        }*/
                    }
                }
                else

                {
                    if (counter == 0 & x6 == array[5]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;


                    } else if (counter == 1 & x6 == array[4]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 2 & x6 == array[3]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 3 & x6 == array[2]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 4 & x6 == array[1]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;

                    } else if (counter == 5 & x6 == array[0]) {
                        b6.setVisibility(View.INVISIBLE);
                        counter++;
                        correctnum++;final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                        if(soundflag) {
                            clickaudio.start();
                            clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();

                                }

                                ;
                            });
                        }
                        points=points+30;
                        if (counter == 6) {
                            counter = 0;
                            level++;
                            //  // points=points+30;

                            if (level > SORTING6BUTTONLEVEL) start9();
                            else
                                start6();
                        }

                    } else {
                        ArrayList<Integer> number = new ArrayList<Integer>();
                        for (int i = 1; i <= 20; ++i) number.add(i);
                        Collections.shuffle(number);
                        //counter = 0;
                        wrong++;
                        points=points-10;
                        vibrate(150);
                        b6.startAnimation(shake);
                       /* x1 = number.get(0);
                        x2 = number.get(1);
                        x3 = number.get(2);
                        x4 = number.get(3);
                        x5 = number.get(4);
                        x6 = number.get(5);

                       b1.setText(Integer.toString(x1));
                        b2.setText(Integer.toString(x2));
                        b3.setText(Integer.toString(x3));
                        b4.setText(Integer.toString(x4));
                        b5.setText(Integer.toString(x5));
                        b6.setText(Integer.toString(x6));
                        listsix(x1, x2, x3, x4, x5, x6);
                        b1.setVisibility(View.VISIBLE);
                        b2.setVisibility(View.VISIBLE);
                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        b5.setVisibility(View.VISIBLE);
                        b6.setVisibility(View.VISIBLE);

                        Random r = new Random();
                        int min = 0;
                        int max = 1;
                        int choice = r.nextInt((max - min) + 1) + min;

                        if(choice<1) ascending=true;
                        else  ascending =false;


                        if (ascending==true){

                            b1.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentascending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentascending));


                        }

                        else
                        {
                            b1.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b2.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b3.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b4.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b5.setBackground(getResources().getDrawable(R.drawable.segmentdescending));
                            b6.setBackground(getResources().getDrawable(R.drawable.segmentdescending));



                        }*/


                    }

                }


            }

        });

    }

    public void showZairesort() {


        final RelativeLayout g = (RelativeLayout) findViewById(R.id.grid12sorting);



        final RelativeLayout buttonframe =  (RelativeLayout) findViewById(R.id.sixchoice);

        final CircularProgressBar c2 = (CircularProgressBar) findViewById(R.id.circularprogressbarsorting);



        SharedPreferences pref;
        pref = getSharedPreferences("info", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        c2.setVisibility(View.INVISIBLE);
        if(points < lowest | lowest ==0 ) lowest = points ;
        if(points  > highest) highest = points;


        scorecounter = scorecounter+points ;


        TextView tscorecounter2 = (TextView) findViewById(R.id.textView10);
        TextView tscorecounter = (TextView) findViewById(R.id.textView10colorcat);
        TextView tscoretounter3 = (TextView) findViewById(R.id.textView10shootercat);


        tscorecounter.setText(Integer.toString((int)(scorecounter*100/LEVELMAX)) + "%");
        tscorecounter2.setText(Integer.toString((int)(scorecounter*100/LEVELMAX)) + "%");
        tscoretounter3.setText(Integer.toString((int)(scorecounter*100/LEVELMAX)) + "%");



        if (scorecounter >= LEVELMAX) {

            tscorecounter.setText(Integer.toString((int)((scorecounter-LEVELMAX)*100/LEVELMAX)) + "%");
            tscorecounter2.setText(Integer.toString((int)((scorecounter-LEVELMAX)*100/LEVELMAX)) + "%");
            tscoretounter3.setText(Integer.toString((int)((scorecounter-LEVELMAX)*100/LEVELMAX)) + "%");
            mathlevel++;
            winfunc();
            scorecounter = scorecounter-LEVELMAX;

        }


        updatelevel();
        calculatedistance();


        buttonframe.setVisibility(View.INVISIBLE);

        g.setVisibility(View.VISIBLE);


        editor.putInt("highestmath", highest);
        editor.putInt("mathlevel", mathlevel);
        editor.putInt("scorecounter", scorecounter);
        editor.commit();


    }

    Button[] buttonarray = new Button[100];
    public void startpickcolor(){




        buttonarray[0] = (Button) findViewById(R.id.buttonpick);
        buttonarray[1] = (Button) findViewById(R.id.button2pick);
        buttonarray[2] = (Button) findViewById(R.id.button3pick);
        buttonarray[3] = (Button) findViewById(R.id.button4pick);
        buttonarray[4] = (Button) findViewById(R.id.button5pick);
        buttonarray[5] = (Button) findViewById(R.id.button6pick);
        buttonarray[6] = (Button) findViewById(R.id.button11pick);
        buttonarray[7] = (Button) findViewById(R.id.button21pick);
        buttonarray[8] = (Button) findViewById(R.id.button31pick);
        buttonarray[9] = (Button) findViewById(R.id.button41pick);
        buttonarray[10] = (Button) findViewById(R.id.button51pick);
        buttonarray[11] = (Button) findViewById(R.id.button61pick);
        buttonarray[12] = (Button) findViewById(R.id.button111pick);
        buttonarray[13] = (Button) findViewById(R.id.button211pick);
        buttonarray[14] = (Button) findViewById(R.id.button311pick);
        buttonarray[15] = (Button) findViewById(R.id.button411pick);
        buttonarray[16] = (Button) findViewById(R.id.button511pick);
        buttonarray[17] = (Button) findViewById(R.id.button611pick);
        buttonarray[18] = (Button) findViewById(R.id.button1111pick);
        buttonarray[19] = (Button) findViewById(R.id.button2111pick);
        buttonarray[20] = (Button) findViewById(R.id.button3111pick);
        buttonarray[21] = (Button) findViewById(R.id.button4111pick);
        buttonarray[22] = (Button) findViewById(R.id.button5111pick);
        buttonarray[23] = (Button) findViewById(R.id.button6111pick);
        buttonarray[24] = (Button) findViewById(R.id.button11111pick);
        buttonarray[25] = (Button) findViewById(R.id.button21111pick);
        buttonarray[26] = (Button) findViewById(R.id.button31111pick);
        buttonarray[27] = (Button) findViewById(R.id.button41111pick);
        buttonarray[28] = (Button) findViewById(R.id.button51111pick);
        buttonarray[29] = (Button) findViewById(R.id.button61111pick);
        buttonarray[30] = (Button) findViewById(R.id.button111111pick);
        buttonarray[31] = (Button) findViewById(R.id.button211111pick);
        buttonarray[32] = (Button) findViewById(R.id.button311111pick);
        buttonarray[33] = (Button) findViewById(R.id.button411111pick);
        buttonarray[34] = (Button) findViewById(R.id.button511111pick);
        buttonarray[35] = (Button) findViewById(R.id.button611111pick);


        final int[]  arraycountercorrect = new int[50];
        int[]  arraycounterincorrect1 = new int[50];
        int[]  arraycounterincorrect2 = new int[50];

        Random r111 = new Random();

        int min11 = 0;
        int max11 = 2;
        final int choice = r111.nextInt((max11 - min11) + 1) + min11;



        Random r1 = new Random();

        int min = 19;
        int max = 23;
        final int rightBoxcorrect = r1.nextInt((max - min) + 1) + min;


        Random r2 = new Random();
        int min1 = 8;
        int max1 = 10;
        int Incorrect1 = r2.nextInt((max1 - min1) + 1) + min1;

        int Incorrect2 = 36-rightBoxcorrect-Incorrect1;



        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 0; i <= 35; ++i) number.add(i);
        Collections.shuffle(number);


        for(int i = 0; i<36; i++)

        {
            if(i<rightBoxcorrect) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                arraycountercorrect[i]=number.get(i);
            }
            if(i>=rightBoxcorrect && i< rightBoxcorrect+Incorrect1) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));

                arraycountercorrect[i]=number.get(i);

            }

            if(i>=rightBoxcorrect+Incorrect1 && i< 36)
            {
                if(choice==0) buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));


                arraycountercorrect[i]=number.get(i);



            }

        }

        final Boolean[] wrongb={true};
        final int[] corretj= {0};
        for(int i = 0; i<36; i++)

        {
            final int[] ii = {i};
            final Button btn = buttonarray[i];
            buttonarray[i].setSoundEffectsEnabled(false);
            buttonarray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int j =0; j<rightBoxcorrect;j++)

                    {
                        if(ii[0] == arraycountercorrect[j]) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            wrongb[0]=false;
                            //buttonarray[ii[0]].setAnimation(shake);
                            corretj[0] = j;
                            points=points+40;
                            if(correctnum>3) start2pickcolor();
                            else startpickcolor();
                            //tcorrect.setText("Correct: " + Integer.toString(correct));

                        }
                        else

                        {

                            //   twrong.setText("Wrong:" + Integer.toString(wrong));
                        }


                    }


                    if(wrongb[0]==true)

                    {
                        vibrate(150);
                        //btn.setAnimation(shake);
                    }
                    tries++;

                    wrong=tries-correctnum;



                }
            });

        }

    }


    public void start2pickcolor(){



        buttonarray[0] = (Button) findViewById(R.id.buttonpick);
        buttonarray[1] = (Button) findViewById(R.id.button2pick);
        buttonarray[2] = (Button) findViewById(R.id.button3pick);
        buttonarray[3] = (Button) findViewById(R.id.button4pick);
        buttonarray[4] = (Button) findViewById(R.id.button5pick);
        buttonarray[5] = (Button) findViewById(R.id.button6pick);
        buttonarray[6] = (Button) findViewById(R.id.button11pick);
        buttonarray[7] = (Button) findViewById(R.id.button21pick);
        buttonarray[8] = (Button) findViewById(R.id.button31pick);
        buttonarray[9] = (Button) findViewById(R.id.button41pick);
        buttonarray[10] = (Button) findViewById(R.id.button51pick);
        buttonarray[11] = (Button) findViewById(R.id.button61pick);
        buttonarray[12] = (Button) findViewById(R.id.button111pick);
        buttonarray[13] = (Button) findViewById(R.id.button211pick);
        buttonarray[14] = (Button) findViewById(R.id.button311pick);
        buttonarray[15] = (Button) findViewById(R.id.button411pick);
        buttonarray[16] = (Button) findViewById(R.id.button511pick);
        buttonarray[17] = (Button) findViewById(R.id.button611pick);
        buttonarray[18] = (Button) findViewById(R.id.button1111pick);
        buttonarray[19] = (Button) findViewById(R.id.button2111pick);
        buttonarray[20] = (Button) findViewById(R.id.button3111pick);
        buttonarray[21] = (Button) findViewById(R.id.button4111pick);
        buttonarray[22] = (Button) findViewById(R.id.button5111pick);
        buttonarray[23] = (Button) findViewById(R.id.button6111pick);
        buttonarray[24] = (Button) findViewById(R.id.button11111pick);
        buttonarray[25] = (Button) findViewById(R.id.button21111pick);
        buttonarray[26] = (Button) findViewById(R.id.button31111pick);
        buttonarray[27] = (Button) findViewById(R.id.button41111pick);
        buttonarray[28] = (Button) findViewById(R.id.button51111pick);
        buttonarray[29] = (Button) findViewById(R.id.button61111pick);
        buttonarray[30] = (Button) findViewById(R.id.button111111pick);
        buttonarray[31] = (Button) findViewById(R.id.button211111pick);
        buttonarray[32] = (Button) findViewById(R.id.button311111pick);
        buttonarray[33] = (Button) findViewById(R.id.button411111pick);
        buttonarray[34] = (Button) findViewById(R.id.button511111pick);
        buttonarray[35] = (Button) findViewById(R.id.button611111pick);

        final int[]  arraycountercorrect = new int[50];
        int[]  arraycounterincorrect1 = new int[50];
        int[]  arraycounterincorrect2 = new int[50];

        Random r111 = new Random();

        int min11 = 0;
        int max11 = 2;
        final int choice = r111.nextInt((max11 - min11) + 1) + min11;



        Random r1 = new Random();

        int min = 16;
        int max = 19;
        final int rightBoxcorrect = r1.nextInt((max - min) + 1) + min;


        Random r2 = new Random();
        int min1 = 10;
        int max1 = 12;
        int Incorrect1 = r2.nextInt((max1 - min1) + 1) + min1;

        int Incorrect2 = 36-rightBoxcorrect-Incorrect1;



        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 0; i <= 35; ++i) number.add(i);
        Collections.shuffle(number);


        for(int i = 0; i<36; i++)

        {
            if(i<rightBoxcorrect) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                arraycountercorrect[i]=number.get(i);
            }
            if(i>=rightBoxcorrect && i< rightBoxcorrect+Incorrect1) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));

                arraycountercorrect[i]=number.get(i);

            }

            if(i>=rightBoxcorrect+Incorrect1 && i< 36)
            {
                if(choice==0) buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));


                arraycountercorrect[i]=number.get(i);



            }

        }


        final Boolean[] wrongb={true};
        final int[] corretj= {0};
        for(int i = 0; i<36; i++)

        {
            final int[] ii = {i};
            buttonarray[i].setSoundEffectsEnabled(false);
            buttonarray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int j =0; j<rightBoxcorrect;j++)

                    {
                        if(ii[0] == arraycountercorrect[j]) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            wrongb[0]=false;
                            corretj[0] = j;
                            points=points+40;
                            if(correctnum>9) start3pickcolor();
                            else start2pickcolor();
                            //tcorrect.setText("Correct: " + Integer.toString(correct));

                        }
                        else

                        {

                            //   twrong.setText("Wrong:" + Integer.toString(wrong));
                        }


                    }


                    if(wrongb[0]==true)
                    {
                        vibrate(150);
                      //  buttonarray[ii[0]].setAnimation(shake);
                    }
                    tries++;

                    wrong=tries-correctnum;



                }
            });

        }

    }



    public void start3pickcolor(){


        //final Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        //shake.setDuration(200);
        buttonarray[0] = (Button) findViewById(R.id.buttonpick);
        buttonarray[1] = (Button) findViewById(R.id.button2pick);
        buttonarray[2] = (Button) findViewById(R.id.button3pick);
        buttonarray[3] = (Button) findViewById(R.id.button4pick);
        buttonarray[4] = (Button) findViewById(R.id.button5pick);
        buttonarray[5] = (Button) findViewById(R.id.button6pick);
        buttonarray[6] = (Button) findViewById(R.id.button11pick);
        buttonarray[7] = (Button) findViewById(R.id.button21pick);
        buttonarray[8] = (Button) findViewById(R.id.button31pick);
        buttonarray[9] = (Button) findViewById(R.id.button41pick);
        buttonarray[10] = (Button) findViewById(R.id.button51pick);
        buttonarray[11] = (Button) findViewById(R.id.button61pick);
        buttonarray[12] = (Button) findViewById(R.id.button111pick);
        buttonarray[13] = (Button) findViewById(R.id.button211pick);
        buttonarray[14] = (Button) findViewById(R.id.button311pick);
        buttonarray[15] = (Button) findViewById(R.id.button411pick);
        buttonarray[16] = (Button) findViewById(R.id.button511pick);
        buttonarray[17] = (Button) findViewById(R.id.button611pick);
        buttonarray[18] = (Button) findViewById(R.id.button1111pick);
        buttonarray[19] = (Button) findViewById(R.id.button2111pick);
        buttonarray[20] = (Button) findViewById(R.id.button3111pick);
        buttonarray[21] = (Button) findViewById(R.id.button4111pick);
        buttonarray[22] = (Button) findViewById(R.id.button5111pick);
        buttonarray[23] = (Button) findViewById(R.id.button6111pick);
        buttonarray[24] = (Button) findViewById(R.id.button11111pick);
        buttonarray[25] = (Button) findViewById(R.id.button21111pick);
        buttonarray[26] = (Button) findViewById(R.id.button31111pick);
        buttonarray[27] = (Button) findViewById(R.id.button41111pick);
        buttonarray[28] = (Button) findViewById(R.id.button51111pick);
        buttonarray[29] = (Button) findViewById(R.id.button61111pick);
        buttonarray[30] = (Button) findViewById(R.id.button111111pick);
        buttonarray[31] = (Button) findViewById(R.id.button211111pick);
        buttonarray[32] = (Button) findViewById(R.id.button311111pick);
        buttonarray[33] = (Button) findViewById(R.id.button411111pick);
        buttonarray[34] = (Button) findViewById(R.id.button511111pick);
        buttonarray[35] = (Button) findViewById(R.id.button611111pick);


        final int[]  arraycountercorrect = new int[50];
        int[]  arraycounterincorrect1 = new int[50];
        int[]  arraycounterincorrect2 = new int[50];

        Random r111 = new Random();

        int min11 = 0;
        int max11 = 2;
        final int choice = r111.nextInt((max11 - min11) + 1) + min11;



        Random r1 = new Random();

        int min = 14;
        int max = 16;
        final int rightBoxcorrect = r1.nextInt((max - min) + 1) + min;


        Random r2 = new Random();
        int min1 = 11;
        int max1 = 13;
        int Incorrect1 = r2.nextInt((max - min) + 1) + min;

        int Incorrect2 = 36-rightBoxcorrect-Incorrect1;



        ArrayList<Integer> number = new ArrayList<Integer>();
        for (int i = 0; i <= 35; ++i) number.add(i);
        Collections.shuffle(number);


        for(int i = 0; i<36; i++)

        {
            if(i<rightBoxcorrect) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                arraycountercorrect[i]=number.get(i);
            }
            if(i>=rightBoxcorrect && i< rightBoxcorrect+Incorrect1) {

                if(choice==0)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));
                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentblue));

                arraycountercorrect[i]=number.get(i);

            }

            if(i>=rightBoxcorrect+Incorrect1 && i< 36)
            {
                if(choice==0) buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));

                else if (choice==1)
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegementgreen));
                else
                    buttonarray[number.get(i)].setBackground(getResources().getDrawable(R.drawable.colorsegmentred));


                arraycountercorrect[i]=number.get(i);



            }

        }


        final Boolean[] wrongb={true};
        final int[] corretj= {0};
        for(int i = 0; i<36; i++)

        {
            final int[] ii = {i};
            buttonarray[i].setSoundEffectsEnabled(false);
            buttonarray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int j =0; j<rightBoxcorrect;j++)

                    {
                        if(ii[0] == arraycountercorrect[j]) {
                            correctnum++;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            wrongb[0]=false;
                           // buttonarray[ii[0]].setAnimation(shake);
                            corretj[0] = j;
                            points=points+40;
                            if(correctnum>3) start2pickcolor();
                            else startpickcolor();
                            //tcorrect.setText("Correct: " + Integer.toString(correct));

                        }
                        else

                        {

                            //   twrong.setText("Wrong:" + Integer.toString(wrong));
                        }


                    }


                    if(wrongb[0]==true)  {
                        vibrate(150);
                      //  buttonarray[ii[0]].setAnimation(shake);
                    }
                    tries++;

                    wrong=tries-correctnum;



                }
            });

        }

    }

    Context xx = this;
    int choice = 0;
    int operandmemcard1 = 0;
    int operandmemcard2 = 0;
    int ascdesc = 0;

    void  startcolorcalculation() {

        final LinearLayout cardslayout = (LinearLayout) findViewById(R.id.cardslayout);
        final LinearLayout cardslayout2 = (LinearLayout) findViewById(R.id.cardslayout22);


        cardslayout.setVisibility(View.VISIBLE);
        cardslayout2.setVisibility(View.INVISIBLE);


        final Button card1 = (Button) findViewById(R.id.buttonmemorycard);
        final Button card2 = (Button) findViewById(R.id.buttonmemory222);
        final Button cardmemorized = (Button) findViewById(R.id.buttonmemorized);
        Random r = new Random();

        ascdesc = r.nextInt(4 - 0) + 0;

        Log.d("random", Integer.toString(ascdesc));
        operandmemcard1 = r.nextInt(10 - 0) + 0;
        operandmemcard2 = r.nextInt(10 - 0) + 0;

        card1.setText(Integer.toString(operandmemcard1));
        card2.setText(Integer.toString(operandmemcard2));


        if (ascdesc == 0) {
            card1.setBackground(getResources().getDrawable(R.drawable.segmentascendingmemcard));
            card2.setBackground(getResources().getDrawable(R.drawable.segmentascendingmemcard));
        } else if (ascdesc == 1) {
            card1.setBackground(getResources().getDrawable(R.drawable.segmentascendingmemcard));
            card2.setBackground(getResources().getDrawable(R.drawable.segmentdescendingmemcard));
        } else if (ascdesc == 2) {
            card1.setBackground(getResources().getDrawable(R.drawable.segmentdescendingmemcard));
            card2.setBackground(getResources().getDrawable(R.drawable.segmentascendingmemcard));
        } else

        {
            card1.setBackground(getResources().getDrawable(R.drawable.segmentdescendingmemcard));
            card2.setBackground(getResources().getDrawable(R.drawable.segmentdescendingmemcard));

        }


        cardmemorized.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        cardmemorized.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        card1.setBackgroundResource(R.mipmap.back);
                        card2.setBackgroundResource(R.mipmap.back);

                        card1.setText("");
                        card2.setText("");


                        AnimatorSet set;
                        set = (AnimatorSet) AnimatorInflater.loadAnimator(xx, R.animator.flip);
                        set.setTarget(card1);
                        set.start();

                        AnimatorSet set1;
                        set1 = (AnimatorSet) AnimatorInflater.loadAnimator(xx, R.animator.flip);
                        set1.setTarget(card2);
                        set1.start();


                        new CountDownTimer(500, 100) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                fourchoice();
                            }

                        }.start();


                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        cardmemorized.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });


    }


    public void fourchoice()

    {

        final LinearLayout cardslayout = (LinearLayout) findViewById(R.id.cardslayout);
        final LinearLayout cardslayout2 = (LinearLayout) findViewById(R.id.cardslayout22);


        Random r = new Random();

        choice = r.nextInt(4 - 0) + 0;

        int result1;



        cardslayout.setVisibility(View.INVISIBLE);
        cardslayout2.setVisibility(View.VISIBLE);

        final  Button mcard1 = (Button) findViewById(R.id.button1memcard2);
        final  Button mcard2 = (Button) findViewById(R.id.button2memcard2);
        final  Button mcard3 = (Button) findViewById(R.id.button3memcard2);
        final  Button mcard4 = (Button) findViewById(R.id.button4memcard2);

        if (ascdesc == 0) {

            result1 = +operandmemcard1+operandmemcard2;

        }

        else if (ascdesc == 1) {

            result1 = operandmemcard1-operandmemcard2;
        }

        else if (ascdesc == 2) {

            result1 = -operandmemcard1+operandmemcard2;
        }

        else

        {
            result1 = -operandmemcard1-operandmemcard2;

        }

        final TextView t = (TextView) findViewById(R.id.textView);

        if (choice == 0)

        {
            mcard1.setText(Integer.toString(result1));
            mcard2.setText(Integer.toString(result1-1));
            mcard3.setText(Integer.toString(result1+1));
            mcard4.setText(Integer.toString(result1+2));
        }
        else if (choice == 1)

        {
            mcard2.setText(Integer.toString(result1));
            mcard3.setText(Integer.toString(result1-1));
            mcard1.setText(Integer.toString(result1+3));
            mcard4.setText(Integer.toString(result1+2));
        }
        else if (choice == 2)

        {
            mcard3.setText(Integer.toString(result1));
            mcard4.setText(Integer.toString(result1-2));
            mcard1.setText(Integer.toString(result1+1));
            mcard2.setText(Integer.toString(result1+2));
        }
        else if (choice == 3)

        {
            mcard4.setText(Integer.toString(result1));
            mcard3.setText(Integer.toString(result1-2));
            mcard1.setText(Integer.toString(result1+1));
            mcard2.setText(Integer.toString(result1+2));
        }

        mcard1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        mcard1.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        if (choice == 0) {

                            score += 60; final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }

                        }
                        else  if (choice == 1) {score -= 10;  vibrate(150);}
                        else if (choice == 2) {score -= 10;  vibrate(150);}
                        else  if (choice == 3) {score -= 10;  vibrate(150);}
                        startcolorcalculation();
                        updatescorecardcalculation();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        mcard1.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        mcard2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        mcard2.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        if (choice == 1) {

                            score += 60; final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }

                        }
                        else  if (choice == 0) {score -= 10;  vibrate(150);}
                        else if (choice == 2){score -= 10;  vibrate(150);}
                        else  if (choice == 3) {score -= 10;  vibrate(150);}
                        startcolorcalculation();
                        updatescorecardcalculation();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        mcard2.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });
        mcard3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        mcard3.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:



                        if (choice == 2) {

                            score += 60; final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }

                        }
                        else  if (choice == 0) {score -= 10;  vibrate(150);}
                        else if (choice == 1) {score -= 10;  vibrate(150);}
                        else  if (choice == 3) {score -= 10;  vibrate(150);}
                        startcolorcalculation();
                        updatescorecardcalculation();

                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        mcard3.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

        mcard4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.setScaleX(BSCALE);
                        v.setScaleY(BSCALE);
                        mcard4.setAlpha(BALPHA);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:

                        if (choice == 3) {

                            score += 60; final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }

                        }
                        else  if (choice == 0) {score -= 10;  vibrate(150);}
                        else if (choice == 1) {score -= 10;  vibrate(150);}
                        else  if (choice == 2) {score -= 10;  vibrate(150);}
                        startcolorcalculation();
                        updatescorecardcalculation();
                        v.setScaleX(1f);
                        v.setScaleY(1f);
                        mcard4.setAlpha(1f);

                        break;
                    case MotionEvent.ACTION_CANCEL: {

                        break;
                    }
                }
                return true;
            }
        });

    }


    public void updatescorecardcalculation()

    {
        TextView tscore = (TextView) findViewById(R.id.textView10cardcalculation);

        if (score <0) score = 0;

        tscore.setText(Integer.toString(score));

    }



    public void startmemory()

    {

        handler = new UpdateCardsHandler();
        loadImages();


        //TextView url = ((TextView)findViewById(R.id.myWebSite));
        //Linkify.addLinks(url, Linkify.WEB_URLS);

        backImage =  getResources().getDrawable(R.mipmap.remember);

       /*
       ((Button)findViewById(R.id.ButtonNew)).setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			newGame();

		}


	});*/

        buttonListener = new ButtonListener();
        buttonListener2 = new ButtonListener2();
        mainTable = (TableLayout)findViewById(R.id.TableLayout03);


        context  = mainTable.getContext();
        newGame(4, 2);
        heightgame=4;
        widthgame=2;

        //Spinner s = (Spinner) findViewById(R.id.Spinner01);
        // ArrayAdapter adapter = ArrayAdapter.createFromResource(
        //         this, R.array.type, android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // s.setAdapter(adapter);

/*
        //s.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected(
                    android.widget.AdapterView<?> arg0,
                    View arg1, int pos, long arg3){

                ((Spinner) findViewById(R.id.Spinner01)).setSelection(0);

                int x,y;

                switch (pos) {
                    case 1:
                        x=4;y=4;
                        break;
                    case 2:
                        x=4;y=5;
                        break;
                    case 3:
                        x=4;y=6;
                        break;
                    case 4:
                        x=5;y=6;
                        break;
                    case 5:
                        x=6;y=6;
                        break;
                    default:
                        return;
                }
                widthgame=x;
                heightgame=y;
                newGame(x,y);

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });*/

      //  Button x = (Button) findViewById(R.id.button);


       // TableRow cont = (TableRow) findViewById(R.id.tabcontinue);
       // cont.setVisibility(View.INVISIBLE);





    }

    private void newGame(int c, int r) {
        ROW_COUNT = r;
        COL_COUNT = c;

        cards = new int [COL_COUNT] [ROW_COUNT];


        mainTable.removeView(findViewById(R.id.TableRow01));
        mainTable.removeView(findViewById(R.id.TableRow02));

        final TableRow tr = ((TableRow)findViewById(R.id.TableRow03));
        tr.removeAllViews();

        mainTable = new TableLayout(context);
        tr.addView(mainTable);
        loadCards();
        for (int y = 0; y < ROW_COUNT; y++) {

            mainTable.addView(createRow(y));
        }




        new CountDownTimer(1000, 10) {
            public void onTick(long milsec) {



            }

            public void onFinish() {

                if(buttonpressed==false) {
                    mainTable.removeAllViews();

                    for (int y = 0; y < ROW_COUNT; y++) {
                        mainTable.addView(createRow2(y));
                    }

                }

                buttonpressed=false;
            }
        }.start();



        firstCard=null;
        //   loadCards();

        turns=0;
        //((TextView)findViewById(R.id.tv1)).setText("Time": "+turns);



    }

    private void loadImages() {
        images = new ArrayList<Drawable>();

        images.add(getResources().getDrawable(R.mipmap.ccard5));
        images.add(getResources().getDrawable(R.mipmap.ccard2));
        images.add(getResources().getDrawable(R.mipmap.ccard3));
        images.add(getResources().getDrawable(R.mipmap.ccard4));
        images.add(getResources().getDrawable(R.mipmap.ccard1));
        images.add(getResources().getDrawable(R.mipmap.ccard6));
        images.add(getResources().getDrawable(R.mipmap.ccard7));
        images.add(getResources().getDrawable(R.mipmap.ccard8));
        /*images.add(getResources().getDrawable(R.mipmap.card9));
        images.add(getResources().getDrawable(R.mipmap.card10));
        images.add(getResources().getDrawable(R.mipmap.card11));
        images.add(getResources().getDrawable(R.mipmap.card12));
        images.add(getResources().getDrawable(R.mipmap.card13));
        images.add(getResources().getDrawable(R.mipmap.card14));
        images.add(getResources().getDrawable(R.mipmap.card15));
        images.add(getResources().getDrawable(R.mipmap.card16));
        images.add(getResources().getDrawable(R.mipmap.card17));
        images.add(getResources().getDrawable(R.mipmap.card18));
        images.add(getResources().getDrawable(R.mipmap.card19));
        images.add(getResources().getDrawable(R.mipmap.card20));
        images.add(getResources().getDrawable(R.mipmap.card21));*/


    }

    private void loadCards(){
        try{
            int size = ROW_COUNT*COL_COUNT;

            Log.i("loadCards()", "size=" + size);

            ArrayList<Integer> list = new ArrayList<Integer>();

            for(int i=0;i<size;i++){
                list.add(new Integer(i));
            }


            Random r = new Random();

            for(int i=size-1;i>=0;i--){
                int t=0;

                if(i>0){
                    t = r.nextInt(i);
                    globalt = t;
                }

                t=list.remove(t).intValue();
                cards[i%COL_COUNT][i/COL_COUNT]=t%(size/2);

                Log.i("loadCards()", "card[" + (i % COL_COUNT) +
                        "][" + (i / COL_COUNT) + "]=" + cards[i % COL_COUNT][i / COL_COUNT]);

                //   barray[(i % COL_COUNT)][(i / COL_COUNT)].setBackgroundDrawable(images.get(cards[i % COL_COUNT][i / COL_COUNT]));



               /* Button b1 = new Button(context);
                int idx=101;
                b1.setId(1*idx);
                b1.setBackgroundDrawable(images.get(cards[1][1]));*/
            }
        }
        catch (Exception e) {
            Log.e("loadCards()", e+"");
        }

        for(int i=0;i<COL_COUNT;i++)
        {
            for(int j=0; j<ROW_COUNT;j++)
            {
                drw[i][j]= images.get(cards[i][j]);

            }

        }


    }


    private TableRow createRow(int y){
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < COL_COUNT; x++) {

            //barray[x][y]=createImageButton(x,y);

            Button b=createImageButton(x,y);

            row.addView(b);
            // createImageButton(x,y).setBackgroundDrawable(images.get(cards[x][y]));

        }


        return row;
    }

    private TableRow createRow2(int y){
        TableRow row = new TableRow(context);
        row.setHorizontalGravity(Gravity.CENTER);

        for (int x = 0; x < COL_COUNT; x++) {

            //barray[x][y]=createImageButton(x,y);
            row.addView(createImageButton2(x, y));
            // createImageButton(x,y).setBackgroundDrawable(images.get(cards[x][y]));

        }
        return row;
    }



    private Button createImageButton(int x, int y){
        Button button = new Button(context);
        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        Drawable draw=drw[x][y];
        button.setBackgroundDrawable(draw);
        final float density = Resources.getSystem().getDisplayMetrics().density;

        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener2);
        button.setSoundEffectsEnabled(false);

        TableRow.LayoutParams p = new TableRow.LayoutParams();
        p.width=(int) density2*78;
        p.height=(int) density2 * 104;
        p.rightMargin =(int) (5*density2); // right-margin = 10dp
        p.topMargin =(int) (5*density2); // right-margin = 10dp
        button.setLayoutParams(p);

        return button;
    }

    private View createImageButton2(int x, int y){
        Button button = new Button(context);
        final float density2 = Resources.getSystem().getDisplayMetrics().density;
        Drawable draw=drw[x][y];

        button.setBackgroundDrawable(backImage);
        button.setId(100 * x + y);
        button.setOnClickListener(buttonListener);
        button.setSoundEffectsEnabled(false);
        TableRow.LayoutParams p = new TableRow.LayoutParams();
        p.width=(int) density2*78;
        p.height=(int) density2 * 104;
        p.rightMargin =(int) (5*density2); // right-margin = 10dp
        p.topMargin =(int) (5*density2); // right-margin = 10dp
        button.setLayoutParams(p);
        return button;
    }


    class ButtonListener2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            synchronized (lock) {

                mainTable.removeAllViews();

                for (int y = 0; y < ROW_COUNT; y++) {
                    mainTable.addView(createRow2(y));
                    buttonpressed = true;
                }
            }
        }

    }

    int MEMORYLEVEL1CORRECT = 5;
    int  MEMORYLEVEL1CORRECT2 = 4;
    int   GAMEPLAYTIMEMEMORY;

    public void memorylevelmanager()

    {

        Log.d("MEMORYTIME", Integer.toString(GAMEPLAYTIMEMEMORY));
        switch(mathlevel)

        {
            case 0:
                GAMEPLAYTIMEMEMORY = 73000;
                MEMORYLEVEL1CORRECT = 45;

                return;
            case 1:
                GAMEPLAYTIMEMEMORY = 70000;
                MEMORYLEVEL1CORRECT = 40;

                return;
            case 2:

                GAMEPLAYTIMEMEMORY = 65000;
                MEMORYLEVEL1CORRECT = 35;

                return;
            case 3:

                GAMEPLAYTIMEMEMORY = 60000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 4:
                GAMEPLAYTIMEMEMORY = 55000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 5:

                GAMEPLAYTIMEMEMORY = 50000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 6:

                GAMEPLAYTIMEMEMORY = 45000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 7:

                GAMEPLAYTIMEMEMORY = 40000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 8:

                GAMEPLAYTIMEMEMORY = 35000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 9:

                GAMEPLAYTIMEMEMORY = 30000;
                MEMORYLEVEL1CORRECT = 35;
                return;
            case 10:
                GAMEPLAYTIMEMEMORY = 25000;
                MEMORYLEVEL1CORRECT = 35;
                return;

        }


        if (mathlevel <= 15  && mathlevel > 10)   {

            GAMEPLAYTIMEMEMORY = 80000;
            MEMORYLEVEL1CORRECT = 1;
        }

        else  if (mathlevel <= 25  && mathlevel > 15)   {

            GAMEPLAYTIMEMEMORY = 75000;
            MEMORYLEVEL1CORRECT = 1;
        }

        else  if (mathlevel <= 35  && mathlevel > 25)   {

            GAMEPLAYTIMEMEMORY = 70000;
            MEMORYLEVEL1CORRECT = 1;
        }

        else  if (mathlevel > 35  )   {

            GAMEPLAYTIMEMEMORY = 65000;
            MEMORYLEVEL1CORRECT = 1;
        }




    }



    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            synchronized (lock) {
                if(firstCard!=null && seconedCard != null){
                    return;
                }
                int id = v.getId();
                int x = id/100;
                int y = id%100;


                turnCard((Button)v,x,y);
                if( ROW_COUNT==4 & COL_COUNT==4 & score == 8)
                {Timer t1 = new Timer(false);

                    TimerTask tt1 = new TimerTask() {

                        @Override
                        public void run() {

                            try{
                                synchronized (lock) {
                                    handler.sendEmptyMessage(0);
                                }
                            }
                            catch (Exception e) {
                                Log.e("E1", e.getMessage());
                            }
                        }
                    };

                    t1.schedule(tt1, 200);
                    newGame(5,5);

                }
            }

        }





        void turnCard(Button button,int x, int y) {
            button.setBackgroundDrawable(images.get(cards[x][y]));
            // Bitmap bitmap = ((BitmapDrawable)images.get(cards[x][y]).getDrawable()).getBitmap(); // get bitmap associated with your imageview
            //button.setImageBitmap(flip(bitmap,FLIP_HORIZONTAL));
            AnimatorSet set;
            set = (AnimatorSet) AnimatorInflater.loadAnimator(x22, R.animator.flip);
            set.setTarget(button);
            set.start();


            if(firstCard==null){
                firstCard = new Card(button,x,y);
            }
            else{

                if(firstCard.x == x && firstCard.y == y){
                    return; //the user pressed the same card
                }

                seconedCard = new Card(button,x,y);

                turns++;

                //   ((TextView)findViewById(R.id.tv1)).setText("Tries: " + turns);


                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try{
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        }
                        catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, TURNTIME);

            }


        }

    }



    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();

            }
        }
        public void checkCards(){


            if (cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]) {
                firstCard.button.setVisibility(View.INVISIBLE);
                seconedCard.button.setVisibility(View.INVISIBLE);
                correcttries++;
                final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                if(soundflag) {
                    clickaudio.start();
                    clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();

                        }

                        ;
                    });
                }
                gamescore[0] = gamescore[0] + 40;
                score++;
                // iq++;
                // TextView scoretext = (TextView) findViewById(R.id.score);




                // ((TextView) findViewById(R.id.textView)).setText("Score: " + score);

                if( ROW_COUNT==2 & COL_COUNT==4 & score == 4 & correctnum<MEMORYLEVEL1CORRECT)

                {
                    correctnum++;


                    //scoretext.setText(Integer.toString(gamescore[0]));

                }
                else if (ROW_COUNT==4 & COL_COUNT==4 & score == 8 & correctnum>=MEMORYLEVEL1CORRECT)

                {


                    correctnum++;


                    //scoretext.setText(Integer.toString(gamescore[0]));
                }



                if( ROW_COUNT==2 & COL_COUNT==4 & score == 4)
                {
                    score=0;

                    //cont.setVisibility(View.VISIBLE);
                    if(correctnum<MEMORYLEVEL1CORRECT)


                    {
                        new CountDownTimer(300, 100) {
                            public void onTick(long milsec) {

                            }

                            public void onFinish() {
                                newGame(4, 2);
                            }
                        }.start();


                    }
                    else {
                        new CountDownTimer(300, 100) {
                            public void onTick(long milsec) {

                            }

                            public void onFinish() {
                                newGame(4, 4);
                            }
                        }.start();


                    }

                }
                else if (ROW_COUNT==4 & COL_COUNT==4 & score == 8)

                {
                    score = 0;

                    //cont.setVisibility(View.VISIBLE);
                    // if (correctnum >= 3 & correctnum < 29) {
                    new CountDownTimer(300, 100) {
                        public void onTick(long milsec) {

                        }

                        public void onFinish() {
                            newGame(4, 4
                            );
                        }
                    }.start();


                    //  }
                   /* else {
                        new CountDownTimer(300, 100) {
                            public void onTick(long milsec) {

                            }

                            public void onFinish() {
                                newGame(4, 3);
                            }
                        }.start();


                    }*/
                }

            } else {
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);
                wrongtries++;
                gamescore[0] = gamescore[0] - 10;
                vibrate(150);
            }

            firstCard = null;
            seconedCard = null;

          /*  if (ROW_COUNT == 2 & COL_COUNT == 3 & correctnum<4)
                newGame(3, 2);


            else if (ROW_COUNT == 2 & COL_COUNT == 3 & correctnum>=4)
                newGame(4, 2);

            else if (ROW_COUNT == 2 & COL_COUNT == 4 & correctnum>=4 & correctnum<9)
                newGame(4, 2);

            else if (ROW_COUNT == 2 & COL_COUNT == 4 & correctnum>=9)
                newGame(4, 3);


            else {
            }*/


        }
    }





void minesweeperlevelmanager()

    {
        switch (mathlevel)

        {

            case 0:

                ROW = 5;
                COL = 5;
                mines = 6;return;

            case 1:

                ROW = 6;
                COL = 5;
                mines = 7;return;

            case 2:

            ROW = 6;
            COL = 6;
                mines = 9;return;

            case 3:

                ROW = 7;
                COL = 6;
                mines = 10;return;

            case 4:

                ROW = 7;
                COL = 7;
                mines = 12;return;


            case 5:

                ROW = 8;
                COL = 7;
                mines = 13;return;
            case 6:

                ROW = 8;
                COL = 7;
                mines = 14;return;
            case 7:

                ROW = 8;
                COL = 8;

                mines = 15;return;
            case 8:

                ROW = 9;
                COL = 8;

                mines = 16;return;
            case 9:

                ROW = 9;
                COL = 9;

                mines = 19;
                return;
        }


        if(mathlevel > 9 && mathlevel< 15)
        {

            ROW = 10;
            COL = 9;
            mines = 20;
        }

        if(mathlevel > 15 && mathlevel< 1000)
        {

            ROW = 10;
            COL = 10;

            mines = 24;
        }


    }


void startminesweeper()

{


    ROW = 8;
    COL = 8;


   mines = 9;

    minesweeperlevelmanager();


    // int time = 0;
    numflags = 0;




    correct = 0;
    // int wrong = 0;
    // int score = 0;
     isHappy = true;
     gameOver = false;
     isFlag = false;
     isStarted = false;

     count = 0;
     width = 0;






    mines = hardMode ? 20 : 10;
    total = ROW * COL - mines;

    FillGame();
    PrintBoard();

    Display display = getWindowManager().getDefaultDisplay();
    width = display.getWidth();
    newBoard();
}

    public void clearBoard() {
        FillGame();
        numflags = 0;

       // ((TextView) findViewById(R.id.mineView)).setText(Integer.toString(mines
         //       - numflags));

        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                block[i][j].setClicked(false);
                block[i][j].setVisibility(View.VISIBLE);
                block[i][j].setText("");
                isFlagged[i][j] = false;
                tgrid[i][j].setVisibility(View.INVISIBLE);
                if (surrounding[i][j] == 0)
                    tgrid[i][j].setText("");
                else
                    tgrid[i][j].setText(Integer.toString(surrounding[i][j]));

            }
        }
    }

    public void FillGame() {
        FillBoard();
        FillSurround();
    }

    public void zeroFlood(int r, int c) { // NOTE: R AND C ARE SWITCHED
        if (r == 0) { // top row
            if (c == 0) { // left top side
                clickIfUnclicked(r, c + 1);
                clickIfUnclicked(r + 1, c);
                clickIfUnclicked(r + 1, c + 1);
            } else if (c == COL - 1) { // right top side
                clickIfUnclicked(r, c - 1);
                clickIfUnclicked(r + 1, c - 1);
                clickIfUnclicked(r + 1, c);
            } else { // middle top
                clickIfUnclicked(r, c - 1);
                clickIfUnclicked(r, c + 1);
                clickIfUnclicked(r + 1, c - 1);
                clickIfUnclicked(r + 1, c);
                clickIfUnclicked(r + 1, c + 1);
            }
        } else if (r == ROW - 1) { // bottom row
            if (c == 0) { // left bottom side
                clickIfUnclicked(r - 1, c);
                clickIfUnclicked(r - 1, c + 1);
                clickIfUnclicked(r, c + 1);
            } else if (c == COL - 1) { // right bottom side
                clickIfUnclicked(r - 1, c - 1);
                clickIfUnclicked(r - 1, c);
                clickIfUnclicked(r, c - 1);
            } else { // middle bottom
                clickIfUnclicked(r - 1, c - 1);
                clickIfUnclicked(r - 1, c);
                clickIfUnclicked(r - 1, c + 1);
                clickIfUnclicked(r, c - 1);
                clickIfUnclicked(r, c + 1);

            }
        } else if (c == 0) { // left middle side
            clickIfUnclicked(r - 1, c);
            clickIfUnclicked(r - 1, c + 1);
            clickIfUnclicked(r, c + 1);
            clickIfUnclicked(r + 1, c);
            clickIfUnclicked(r + 1, c + 1);
        } else if (c == COL - 1) { // right middle side
            clickIfUnclicked(r - 1, c - 1);
            clickIfUnclicked(r - 1, c);
            clickIfUnclicked(r, c - 1);
            clickIfUnclicked(r + 1, c - 1);
            clickIfUnclicked(r + 1, c);
        } else { // all other cases
            clickIfUnclicked(r - 1, c - 1);
            clickIfUnclicked(r - 1, c);
            clickIfUnclicked(r - 1, c + 1);
            clickIfUnclicked(r, c - 1);
            clickIfUnclicked(r, c + 1);
            clickIfUnclicked(r + 1, c - 1);
            clickIfUnclicked(r + 1, c);
            clickIfUnclicked(r + 1, c + 1);
        }
    }

    public void clickIfUnclicked(int r, int c) {
        if (!block[r][c].getClicked())
            block[r][c].performClick();
    }

    public void newBoard() {
        Block b;
        TextView tv;

        int xmargin = 0, ymargin = 0;
        int bSize = width / COL;

       final RelativeLayout gv = (RelativeLayout) findViewById(R.id.grid);
        RelativeLayout.LayoutParams rel_b;

        // Vibrate for 500 milliseconds

        block = new Block[COL][ROW];
        tgrid = new TextView[COL][ROW];
        final Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {

                rel_b = new RelativeLayout.LayoutParams(bSize, bSize);
                rel_b.leftMargin = xmargin;
                rel_b.topMargin = ymargin;

                b = new Block(this);
                b.setId(count);
                b.setLayoutParams(rel_b);


                //b.setBackgroundColor(Color.RED);
                b.setBackgroundResource(R.drawable.borderfull);
                //tgrid[b.getyPos()][b.getxPos()]
                //		.setTextSize(18);

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Block b = (Block) v;
                        b.setClicked(true);

                        if (gameOver || isFlagged[b.getyPos()][b.getxPos()])
                            return;

                        isStarted = true;

                        if (inUse[b.getyPos()][b.getxPos()]) {
                            //gameOver = true;

                            score = score - 5;


                            updateminesweeper();

                            vibs.vibrate(200);
                            //b.setBackgroundResource(R.drawable.minesweeper);
                            System.out.println("Boom - y, x: " + b.getyPos()
                                    + b.getxPos());
                           // ((Button) findViewById(R.id.face))
                             //       .setBackgroundResource(R.drawable.sad);
                            block[b.getyPos()][b.getxPos()]
                                    .setBackgroundResource(R.mipmap.minesweeper);
                            block[b.getyPos()][b.getxPos()]
                                    .setText("");
                            //return;
                        } else {


                            score = score + 15;
                            final MediaPlayer clickaudio = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                            if(soundflag) {
                                clickaudio.start();
                                clickaudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer mp) {
                                        mp.release();

                                    }

                                    ;
                                });
                            }
                            updateminesweeper();
                            block[b.getyPos()][b.getxPos()]
                                    .setVisibility(View.INVISIBLE);
                            tgrid[b.getyPos()][b.getxPos()].setBackgroundResource(R.drawable.bordermine);
                            tgrid[b.getyPos()][b.getxPos()].setTextColor(Color.WHITE);
                            tgrid[b.getyPos()][b.getxPos()].setTextSize(16);
                            tgrid[b.getyPos()][b.getxPos()].setTypeface(null, Typeface.BOLD);
                            tgrid[b.getyPos()][b.getxPos()]
                                    .setVisibility(View.VISIBLE);
                        }

                        // test for zeroflooding-------
                        if (surrounding[b.getyPos()][b.getxPos()] == 0) {
                            zeroFlood(b.getyPos(), b.getxPos());
                        }
                        // end test for zeroflooding------


                        //tgrid[b.getyPos()][b.getxPos()]
                        //		.setVisibility(View.VISIBLE);

                        count++;

                        if (count == total) {
                            // Win
                          //  ((Button) findViewById(R.id.face))
                            //        .setBackgroundResource(R.drawable.win);
                            gameOver = true;
                            // attempt highscore adding
                            System.out.print("Time was " + time);

                            gv.removeAllViews();
                            startminesweeper();
                            //popUpScreen(addScoreFromGame(time));

                        }

                    }
                });

                b.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Block b = (Block) v;
                        isFlagged[b.getyPos()][b.getxPos()] = !isFlagged[b
                                .getyPos()][b.getxPos()];
                        if (isFlagged[b.getyPos()][b.getxPos()]) {
                            b.setText("F");
                            numflags++;
                        } else {
                            b.setText("");
                            numflags--;
                        }
                        //((TextView) findViewById(R.id.mineView))
                          //      .setText(Integer.toString(mines - numflags));

                        return true;
                    }
                });

                b.setGravity(Gravity.CENTER);

                gv.addView(b);

                tv = new TextView(this);
                tv.setLayoutParams(rel_b);
                tv.setVisibility(View.INVISIBLE);

                if (surrounding[i][j] == 0)
                    tv.setText("");
                else
                    tv.setText(Integer.toString(surrounding[i][j]));
                tv.setGravity(Gravity.CENTER);
                gv.addView(tv);

                b.setxPos(j);
                b.setyPos(i);

                block[i][j] = b;
                tgrid[i][j] = tv;

                xmargin += bSize;
            }
            ymargin += bSize;
            xmargin = 0;
        }
    }

    /*
     * Creates and adds mines to the board.
     */
    public void FillBoard() {
        int i = 0;
        int colRand, rowRand;
        Random rand = new Random();

        inUse = new boolean[COL][ROW];
        isFlagged = new boolean[COL][ROW];

        count = 0;

        while (i < mines) {
            colRand = rand.nextInt(COL);
            rowRand = rand.nextInt(ROW);

            if (!inUse[colRand][rowRand])
                inUse[colRand][rowRand] = true;
            else
                continue;

            i++;
        }

    }

    /*
     * Check the immediate surroundings (8 or less grids) at a position [c,r]
     * and return the number of mines surrounding it. INPUT: Row position,
     * Column position OUTPUT: Number of mines surrounding the position
     */
    public int CheckSurround(int r, int c) {
        int count = 0;

        if (r - 1 >= 0) {
            if (inUse[c][r - 1])
                count++;

            if (c - 1 >= 0)
                if (inUse[c - 1][r - 1])
                    count++;
            if (c + 1 < COL)
                if (inUse[c + 1][r - 1])
                    count++;
        }

        if (r + 1 < ROW) {
            if (inUse[c][r + 1])
                count++;

            if (c - 1 >= 0)
                if (inUse[c - 1][r + 1])
                    count++;
            if (c + 1 < COL)
                if (inUse[c + 1][r + 1])
                    count++;
        }

        if (c - 1 >= 0)
            if (inUse[c - 1][r])
                count++;

        if (c + 1 < COL)
            if (inUse[c + 1][r])
                count++;

        return count;
    }

    /*
     * Populate the number board based on mine position. Uses CheckSurround().
     */
    public void FillSurround() {

        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {

                if (inUse[i][j])
                    surrounding[i][j] = 9;
                else
                    surrounding[i][j] = CheckSurround(j, i);
            }
        }
    }

    public void PrintBoard() {
        System.out.println("SURROUNDING");

        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                System.out.print(surrounding[i][j] + " ");
            }
            System.out.println();
        }

        int test;
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("BOMBS 1 = BOMB");
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (inUse[i][j])
                    test = 1;
                else
                    test = 0;
                System.out.print(test + " ");
            }
            System.out.println();
        }
    }

    /*
     * Adds a score into the EASY or HARD high score list, if it is in the top 5
     * INPUT: New score to possibly be added OUTPUT: TRUE if a score was
     * inserted, FALSE if not.
     */
    public boolean addScoreFromGame(int newscore) {
        SharedPreferences scores = getSharedPreferences("scoreList", 0);
        int easybound = scores.getInt("e4", 999);
        int hardbound = scores.getInt("h4", 999);

        // criteria for adding a new hard high score
        if (hardMode && newscore < hardbound) {
            String key = "";
            // grab the top 4 hard scores
            int[] arr = new int[4];
            for (int i = 0; i < 4; i++) {
                key = "h".concat(Integer.toString(i));
                arr[i] = scores.getInt(key, 999);
            }
            reSort(arr, newscore);
            return true;
        }

        // criteria for adding a new easy high score
        else if (!hardMode && newscore < easybound) {
            String key = "";
            // grab the top 4 easy scores
            int[] arr = new int[4];
            for (int i = 0; i < 4; i++) {
                key = "e".concat(Integer.toString(i));
                arr[i] = scores.getInt(key, 999);
            }
            reSort(arr, newscore);
            return true;
        }

        return false;
    }

    /*
     * Sorts and inserts a new score into a corresponding HARD/EASY high score
     * saved data. INPUT: array of integers representing the top 4 scores, the
     * new score to be added
     */
    public void reSort(int[] arr, int newscore) {
        SharedPreferences scores = this.getSharedPreferences("scoreList",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor adder = scores.edit();
        String mode = hardMode ? "h" : "e";
        String addkey = "";
        for (int i = 3; i >= 0; i--) {
            addkey = mode.concat(Integer.toString(i + 1));
            if (arr[i] > newscore) {
                adder.putInt(addkey, arr[i]);
            } else {
                adder.putInt(addkey, newscore);
                adder.commit();
                return; // stop shifting values
            }
        }

        // if we've reached here, we need to push the newscore to the first
        // position
        addkey = mode.concat(Integer.toString(0));
        adder.putInt(addkey, newscore);
        adder.commit();
    }

    private void setFullscreen()
    {
        if (fullscreen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        mGameView.requestLayout();
    }

    public synchronized static void setMode(int newMode)
    {
        gameMode = newMode;
    }

    public synchronized static int getMode()
    {
        return gameMode;
    }

    public synchronized static boolean getSoundOn()
    {
        return soundOn;
    }

    public synchronized static void setSoundOn(boolean so)
    {
        soundOn = so;
    }

    public synchronized static boolean getAimThenShoot()
    {
        return aimThenShoot;
    }

    public synchronized static void setAimThenShoot(boolean ats)
    {
        aimThenShoot = ats;
    }

    public synchronized static boolean getDontRushMe()
    {
        return dontRushMe;
    }

    public synchronized static void setDontRushMe(boolean dont)
    {
        dontRushMe = dont;
    }

   public int startinglevel2 = 15;
    @Override
    protected void onNewIntent(Intent intent) {
        if (null != intent && EDITORACTION.equals(intent.getAction())) {
            if (!activityCustomStarted) {
                activityCustomStarted = true;

                // Get custom level last played.
                SharedPreferences sp = getSharedPreferences(
                        FrozenBubble.PREFS_NAME, Context.MODE_PRIVATE);
                int startingLevel = sp.getInt("levelCustom", 0);


                int startingLevelIntent = intent.getIntExtra("startingLevel", -2);
                startingLevel = (startingLevelIntent == -2) ?
                        startingLevel : startingLevelIntent;

                mGameView = null;
                mGameView = new GameView(
                        this, intent.getExtras().getByteArray("levels"),
                        startinglevel2);
                setContentView(mGameView);
                mGameThread = mGameView.getThread();
                //mGameThread = mGameView.getThread().resume();

                mGameThread.newGame(20);
                mGameView.requestFocus();
                setFullscreen();
            }
        }
    }

    public void showAd(){
        ((Activity)ctx).runOnUiThread(new Runnable() {

            @Override
            public void run() {


                Handler innerHandler = new Handler();

                // SessionM.getInstance().presentActivity(((Activity)ctx),"text sent");


                Looper.loop();
            }
        });


    }


    // Starts editor / market with editor's download.
    private void startEditor()
    {
        Intent i = new Intent();
        // First try to run the plus version of Editor.
        i.setClassName("sk.halmi.fbeditplus",
                "sk.halmi.fbeditplus.EditorActivity");
        try {
            startActivity(i);
            finish();
        } catch (ActivityNotFoundException e) {
            // If not found, try to run the normal version.
            i.setClassName("sk.halmi.fbedit",
                    "sk.halmi.fbedit.EditorActivity");
            try {
                startActivity(i);
                finish();
            } catch (ActivityNotFoundException ex) {
                // If user doesnt have Frozen Bubble Editor take him to market.
                try {
                    Toast.makeText(getApplicationContext(),
                            R.string.install_editor, 1000).show();
                    i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(
                                    "market://search?q=frozen bubble level editor"));
                    startActivity(i);
                } catch (Exception exc) {
                    // Damn you don't have market?
                    Toast.makeText(getApplicationContext(),
                            R.string.market_missing, 1000).show();
                }
            }
        }
    }

    public static final String TIME_SERVER = "ntp02.oal.ul.pt";

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }



    public static Long getNetworkTime() throws IOException {
        NTPUDPClient timeClient = new NTPUDPClient();
        timeClient.setDefaultTimeout(5000);
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
        TimeInfo timeInfo = timeClient.getTime(inetAddress);

       // return new Date(timeInfo.getReturnTime());

        return timeInfo.getMessage().getReceiveTimeStamp().getTime();
    }


    public final class PublicServerTime {

        public  Date getNTPDate() {

            String[] hosts = new String[]{
                    "ntp02.oal.ul.pt", "ntp04.oal.ul.pt",
                    "ntp.xs4all.nl"};


            NTPUDPClient client = new NTPUDPClient();
            // We want to timeout if a response takes longer than 5 seconds
            client.setDefaultTimeout(5000);

            for (String host : hosts) {

                try {

                    InetAddress hostAddr = InetAddress.getByName(host);
                    System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
                    TimeInfo info = client.getTime(hostAddr);
                    Date date = new Date(info.getReturnTime());
                    Log.d("sunnydate:","" +  date);

                    return date;

                }

                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            client.close();

            return null;

        }


        public  final void main(String[] args) {

            //Log.d("yigitdate: ", " " + getNTPDate());

        }

    }
}
