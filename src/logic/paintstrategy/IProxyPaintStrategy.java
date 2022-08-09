package logic.paintstrategy;

public interface IProxyPaintStrategy extends IPaintStrategy {
    void setSelected(boolean isSelected);
    IPaintStrategy getPaintStrategy();
}
