package com.fragment.navigation.jabed.apraisesend;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;


import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarouselPreviewActivity extends AppCompatActivity {

    public static int INVALID_POSITION = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carousel_preview);

        //final ActivityCarouselPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_carousel_preview);
        Toolbar tbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tbar);

        final HorizontalAdaptar adapter = new HorizontalAdaptar(this);
        final VerticalAdaptar verticalAdaptar= new VerticalAdaptar(this);

        RecyclerView rh = (RecyclerView) findViewById(R.id.list_horizontal);
        RecyclerView rv = (RecyclerView) findViewById(R.id.list_vertical);
        // create layout manager with needed params: vertical, cycle
        initRecyclerView(rh, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), adapter);
        initVerRecyclerView(rv, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), verticalAdaptar);

    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final HorizontalAdaptar adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
                }
            }
        });
    }

    private void initVerRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final VerticalAdaptar adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (INVALID_POSITION != adapterPosition) {
                    final int value = adapter.mPosition[adapterPosition];
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
                }
            }
        });
    }

    private static final class HorizontalAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @SuppressWarnings("UnsecureRandomNumberGeneration")
        private final Random mRandom = new Random();
        private final int[] mColors;
        private final int[] mPosition;
        private Context context;
        private final int[] image={
            R.drawable.f1,
                R.drawable.f2,
                R.drawable.f3,
                R.drawable.f4,
                R.drawable.f5,
                R.drawable.f6,
                R.drawable.f7,
                R.drawable.f8,
                R.drawable.f9,
                R.drawable.f10,

        };
        private final String[] title={
                "Hasib Prince",
                "Ifakhar Hossain",
                "Jin Yean",
                "Victor 2.0",
                "Badiuzzaman",
                "Minhazur Rahman",
                "Jeeva",
                "Anik Islam Abhi",
                "Saad Mahmood",
                "Chin Sze Yen",

        };


        private int mItemsCount = 10;
        LayoutInflater inflater;

        HorizontalAdaptar(Context context) {
            this.context=context;

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mColors = new int[10];
            mPosition = new int[10];

            for (int i = 0; 10 > i; ++i) {
                //noinspection MagicNumber
                mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
                mPosition[i] = i;

            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


                View view = inflater.inflate( R.layout.item_view, null) ;
                RecyclerView.ViewHolder holder = new RowNewsViewHolder(view);
                return holder;

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((RowNewsViewHolder) holder).cItem1.setText(title[position]);

            ((RowNewsViewHolder) holder).pp.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), image[position], null));

            //((RowNewsViewHolder) holder).pp.setBackgroundResource(image[position]);
            //((RowNewsViewHolder) holder).cItem2.setText(String.valueOf(mPosition[position]));

            //holder.itemView.setBackgroundColor(mColors[position]);
        }

        @Override
        public int getItemCount() {
            return mItemsCount;
        }
    }

    public static class RowNewsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView apraisorProfilePic;
        TextView cItem1;
        CircleImageView pp;


        public RowNewsViewHolder(View itemView) {
            super(itemView);

            cItem1 = (TextView) itemView.findViewById(R.id.c_item_1);
            pp = (CircleImageView)itemView.findViewById(R.id.profilePicture);
           // cItem2 = (TextView) itemView.findViewById(R.id.c_item_2);

        }
    }

    private static final class VerticalAdaptar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @SuppressWarnings("UnsecureRandomNumberGeneration")
        private final Random mRandom = new Random();
        private final int[] mColors;
        private final int[] mPosition;
        private final int[] image;
        private int mItemsCount = 10;
        LayoutInflater inflater;

        private final String[] card_title={
                "Customer focus/ customer service",
                "Change management",
                "Self improvement",
                "Teamwork",
                "Goal setting",
                "Coaching and teaching",
                "Time management",
                "Decision making/ Problem solving",
                "Valuing diversity",
                "Initiative",

        };
        private final String[] card_desc={
                "Ability to understand customers’ problems, follow-up and solve their problems in a manner that increases customer satisfaction.",
                "Adaptability to uncertainty and ambiguity by understanding and planning for change, managing resistance to change, and implementing change.",
                "Continuously learning and actively acquiring new skill to develop one self as well as applying new skill quickly.",
                "Consistently encourage a work environment in which individuals feel free to work together with others and to share information and ideas in a context of mutual respect and trust.",
                "Sets individual goals that are realistic and achievable, and aligns to company goals.",
                "Structures learning effectively and motivate others positively by providing practice and feedback.",
                "Effectively balance task and responsibility, assesses priorities daily and prioritize based on company needs.",
                "Able to identify key issues in complex situation, analyze problems and make sound decisions, and assume accountability for own actions.",
                "Understanding of cultural differences among colleagues and valuing their input towards company objectives.",
                "Proactive in generating ideas for improvement, solving problems without being asked and takes advantage of opportunities as they arises.",

        };

        VerticalAdaptar(Context context) {

            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mColors = new int[10];
            mPosition = new int[10];
            image = new int[10];
            for (int i = 0; 10 > i; ++i) {
                //noinspection MagicNumber
                mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
                mPosition[i] = i;

            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = inflater.inflate( R.layout.item_card, null) ;
            RecyclerView.ViewHolder holder = new RowVerNewsViewHolder(view);
            return holder;

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((RowVerNewsViewHolder) holder).cItem1.setText(card_title[position]);
            ((RowVerNewsViewHolder) holder).cItem2.setText(card_desc[position]);

            //holder.itemView.setBackgroundColor(mColors[position]);
        }

        @Override
        public int getItemCount() {
            return mItemsCount;
        }
    }

    public static class RowVerNewsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView apraisorProfilePic;
        TextView cItem1;
        TextView cItem2;


        public RowVerNewsViewHolder(View itemView) {
            super(itemView);

            cItem1 = (TextView) itemView.findViewById(R.id.skillTitle);
             cItem2 = (TextView) itemView.findViewById(R.id.skillDetails);

        }
    }
}