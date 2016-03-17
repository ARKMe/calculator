package bello.andrea.calcolatrice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import bello.andrea.calcolatrice.operations.Addition;
import bello.andrea.calcolatrice.operations.Division;
import bello.andrea.calcolatrice.operations.Multiplication;
import bello.andrea.calcolatrice.operations.Operation;
import bello.andrea.calcolatrice.operations.Subtraction;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplayedResultTextView;
    private Operation mCurrentOperation;
    private boolean mReadyToReceiveNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mDisplayedResultTextView = (TextView)findViewById(R.id.result);
        mReadyToReceiveNumber = true;

        findViewById(R.id.button0).setOnClickListener(new NumberButtonListener(0));
        findViewById(R.id.button1).setOnClickListener(new NumberButtonListener(1));
        findViewById(R.id.button2).setOnClickListener(new NumberButtonListener(2));
        findViewById(R.id.button3).setOnClickListener(new NumberButtonListener(3));
        findViewById(R.id.button4).setOnClickListener(new NumberButtonListener(4));
        findViewById(R.id.button5).setOnClickListener(new NumberButtonListener(5));
        findViewById(R.id.button6).setOnClickListener(new NumberButtonListener(6));
        findViewById(R.id.button7).setOnClickListener(new NumberButtonListener(7));
        findViewById(R.id.button8).setOnClickListener(new NumberButtonListener(8));
        findViewById(R.id.button9).setOnClickListener(new NumberButtonListener(9));

        findViewById(R.id.plus_sign_button).setOnClickListener(new OperationalSignButtonListener(new Addition()));
        findViewById(R.id.minus_sign_button).setOnClickListener(new OperationalSignButtonListener(new Subtraction()));
        findViewById(R.id.times_sign_button).setOnClickListener(new OperationalSignButtonListener(new Multiplication()));
        findViewById(R.id.division_sign_button).setOnClickListener(new OperationalSignButtonListener(new Division()));

        findViewById(R.id.equals_sign_button).setOnClickListener(new ResultButtonListener());
    }

    private class NumberButtonListener implements View.OnClickListener {

        private int value;

        public NumberButtonListener(int value) {
            this.value = value;
        }

        @Override
        public void onClick(View v) {
            String displayedNumberString = mDisplayedResultTextView.getText().toString();
            if(mReadyToReceiveNumber){
                mDisplayedResultTextView.setText("" + value);
                mReadyToReceiveNumber = false;
            } else {
                mDisplayedResultTextView.setText(displayedNumberString + value);
            }
        }
    }

    private class OperationalSignButtonListener implements View.OnClickListener{

        private Operation mOperation;

        public OperationalSignButtonListener(Operation operation) {
            this.mOperation = operation;
        }

        @Override
        public void onClick(View v) {
            if(mCurrentOperation == null) {
                mCurrentOperation = mOperation;
                mCurrentOperation.setOperator1(Integer.parseInt(mDisplayedResultTextView.getText().toString()));
            } else{
                if(mReadyToReceiveNumber)
                    return;
                mCurrentOperation.setOperator2(Integer.parseInt(mDisplayedResultTextView.getText().toString()));
                mOperation.setOperator1(mCurrentOperation.calculate());
                mCurrentOperation = mOperation;
                mDisplayedResultTextView.setText(mCurrentOperation.getOperator1().toString());
            }
            mReadyToReceiveNumber = true;
        }
    }

    private class ResultButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(mReadyToReceiveNumber)
                return;

            mCurrentOperation.setOperator2(Integer.parseInt(mDisplayedResultTextView.getText().toString()));
            if(mCurrentOperation.getOperator1() != null && mCurrentOperation.getOperator2() != null) {
                mDisplayedResultTextView.setText(mCurrentOperation.calculate().toString());
                mReadyToReceiveNumber = true;
                mCurrentOperation = null;
            }
        }
    }

}
