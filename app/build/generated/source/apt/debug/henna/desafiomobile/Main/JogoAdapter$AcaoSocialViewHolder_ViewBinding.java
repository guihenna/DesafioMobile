// Generated code from Butter Knife. Do not modify!
package henna.desafiomobile.Main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import henna.desafiomobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JogoAdapter$AcaoSocialViewHolder_ViewBinding implements Unbinder {
  private JogoAdapter.AcaoSocialViewHolder target;

  private View view2131230808;

  @UiThread
  public JogoAdapter$AcaoSocialViewHolder_ViewBinding(final JogoAdapter.AcaoSocialViewHolder target,
      View source) {
    this.target = target;

    View view;
    target.imgJogo = Utils.findRequiredViewAsType(source, R.id.as_image, "field 'imgJogo'", ImageView.class);
    target.txtNome = Utils.findRequiredViewAsType(source, R.id.as_name, "field 'txtNome'", TextView.class);
    view = Utils.findRequiredView(source, R.id.item_recycler, "method 'onItemClick'");
    view2131230808 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onItemClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    JogoAdapter.AcaoSocialViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imgJogo = null;
    target.txtNome = null;

    view2131230808.setOnClickListener(null);
    view2131230808 = null;
  }
}
