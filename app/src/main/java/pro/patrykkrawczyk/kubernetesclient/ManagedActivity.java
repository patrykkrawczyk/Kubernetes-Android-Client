package pro.patrykkrawczyk.kubernetesclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import lombok.Getter;
import lombok.Setter;
import org.greenrobot.eventbus.EventBus;

public abstract class ManagedActivity<T extends RecyclerView.Adapter> extends AppCompatActivity {

    @BindView(R.id.boomMenuButton)
    BoomMenuButton boomMenuButton;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Getter
    @Setter
    protected T adapter;

    protected abstract void initializeAdapterData();
    protected abstract int getLayout();
    protected abstract NavigationButton[] getNavigationButtons();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        NavigationButton[] navButtons = getNavigationButtons();
        for (int i = 0; i < navButtons.length; i++) {
            NavigationButton button = navButtons[i];
            addMenuButton(this, button.getName(), button.getActivity());
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        initializeAdapterData();
    }

    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {}

    private void addMenuButton(final Context context, String title, final Class<?> activity) {
        boomMenuButton.addBuilder(new HamButton.Builder()
            .listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int i) {
                    Intent intent = new Intent(context, activity);
                    startActivity(intent);
                }
            })
            .normalText(title));
    }
}
