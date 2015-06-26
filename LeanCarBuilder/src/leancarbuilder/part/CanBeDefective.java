package leancarbuilder.part;

public abstract class CanBeDefective extends Part {

	protected boolean defective = true;

	public boolean isDefective() {
		return defective;
	}

}