// Generated code from Butter Knife. Do not modify!
package henna.desafiomobile.Detalhe;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import henna.desafiomobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetalheActivity_ViewBinding implements Unbinder {
  private DetalheActivity target;

  private View view2131230760;

  @UiThread
  public DetalheActivity_ViewBinding(DetalheActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetalheActivity_ViewBinding(final DetalheActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.circleImageView = Utils.findRequiredViewAsType(source, R.id.as_image, "field 'circleImageView'", ImageView.class);
    target.releaseDateJogo = Utils.findRequiredViewAsType(source, R.id.as_rel, "field 'releaseDateJogo'", TextView.class);
    target.platformsJogo = Utils.findRequiredViewAsType(source, R.id.as_plat, "field 'platformsJogo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.botao, "method 'onClickButtonSite'");
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickButtonSite();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetalheActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.circleImageView = null;
    target.releaseDateJogo = null;
    target.platformsJogo = null;

    view2131230760.setOnClickListener(null);
    view2131230760 = null;
  }
}
