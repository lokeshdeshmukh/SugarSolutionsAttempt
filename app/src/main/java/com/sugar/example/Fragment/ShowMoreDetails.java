package com.sugar.example.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sugar.example.R;
import com.sugar.example.adapter.ViewPagerAdapter;
import com.sugar.example.model.pojo.ProductDetails;

public class ShowMoreDetails extends Fragment {
    ProductDetails object;
    TextView titleText,descText,priceOfProduct;
    View view;
    private LinearLayout llPagerDots;
    private ImageView[] ivArrayDotsPager;
    ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.product_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        object=this.getArguments().getParcelable("data");
        this.view=view;
        initialSetup();
    }
    void initialSetup()
    {
        viewPager = view.findViewById(R.id.productImageViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getContext(),object.getImages());
        llPagerDots = (LinearLayout) view.findViewById(R.id.pager_dots);

        titleText=view.findViewById(R.id.titleText);
        descText=view.findViewById(R.id.descText);
        priceOfProduct=view.findViewById(R.id.priceOfProduct);


        priceOfProduct.setText("Price: "+object.getVariants().get(0).getPrice());
        titleText.setText(object.getTitle());
        descText.setText(Html.fromHtml(object.getBody_html()));

        viewPager.setAdapter(adapter);
        setupPagerIndidcatorDots(object.getImages().size());

    }
    private void setupPagerIndidcatorDots(int size) {



        ivArrayDotsPager = new ImageView[size];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 15, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.selected_viewpagercircle);
            //ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            llPagerDots.addView(ivArrayDotsPager[i]);
//            llPagerDots.bringToFront();
        }
        ivArrayDotsPager[0].setImageResource(R.drawable.selected_viewpagercircle);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.unselected_viewpagercircle);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_viewpagercircle);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
