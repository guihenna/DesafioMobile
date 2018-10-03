// Generated code from Butter Knife. Do not modify!
package henna.desafiomobile.Main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import henna.desafiomobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AcaoSocialActivity_ViewBinding implements Unbinder {
  private JogoActivity target;

  @UiThread
  public AcaoSocialActivity_ViewBinding(JogoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AcaoSocialActivity_ViewBinding(JogoActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.asRV, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    JogoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.toolbar = null;
    target.progressBar = null;
  }
}
