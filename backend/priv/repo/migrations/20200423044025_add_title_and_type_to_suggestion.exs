defmodule IGovern.Repo.Migrations.AddTitleAndTypeToSuggestion do
  use Ecto.Migration

  def change do
    alter table(:suggestions) do
      add :title, :string
      add :type, :string
    end
  end
end
