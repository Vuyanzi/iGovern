defmodule IGovern.Repo.Migrations.AddNameToSuggestions do
  use Ecto.Migration

  def change do
    alter table(:suggestions) do
      add :username, :string
    end
  end
end
