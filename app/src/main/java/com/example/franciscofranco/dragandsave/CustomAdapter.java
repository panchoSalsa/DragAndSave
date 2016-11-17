package com.example.franciscofranco.dragandsave;

import android.content.ClipData;
import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by FranciscoFranco on 11/13/16.
 */
public class CustomAdapter extends BaseAdapter {

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public TextView txtView;

    public CustomAdapter(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages, View txtView) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        this.txtView = (TextView) txtView;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item, null);
        //rowView.setOnTouchListener(new MyCustomTouchListener());
        ImageView img = (ImageView) rowView.findViewById(R.id.imageView1);
        img.setOnTouchListener(new MyCustomTouchListener());
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv.setText(result[i]);
        holder.img.setImageResource(imageId[i]);
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked " + result[i], Toast.LENGTH_LONG).show();
//            }
//        });
        return rowView;
    }

    public final class MyCustomTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //MainActivity.txtView.setText("Drop to Save");

                Vibrator vb = (Vibrator)  context.getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);

                txtView.setText("Drop to Save");

                ClipData data = ClipData.newPlainText("", "");
//                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
//                        (ImageView) view.findViewById(R.id.imageView1));
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }
}
