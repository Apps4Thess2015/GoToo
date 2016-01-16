package dim.theo.thessapp.views;

import com.afollestad.materialdialogs.MaterialDialog;

import dim.theo.thessapp.R;

/**
 * Created by css on 1/16/16.
 */
public class PreferenceDialog extends MaterialDialog {

    public PreferenceDialog(Builder builder) {
        super(builder);
        this.setContentView(R.layout.dialog_preferences);
    }

}
