defmodule IGovern.Repo.Migrations.ChangeContentToText do
  use Ecto.Migration

  def change do
    alter table(:suggestions) do
      modify :content, :text
    end
  end
end
