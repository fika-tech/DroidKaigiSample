import SwiftUI
import shared

struct MonsterListItem: View {
    let monster: Monster
    let onClick: (Monster) -> Void
    
    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            AsyncImage(
                url: URL(string: monster.imageUrl)
            ) { image in
                image
                    .resizable()
                    .aspectRatio(1, contentMode: .fit)
                    .frame(width: 36, height: 36)
            } placeholder: {
                EmptyView()
            }
            .frame(width: 36, height: 36)

            Text(monster.name)
                .font(.system(size: 18))
            Spacer()
        }
        .frame(height: 54)
        .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
    }
}

struct MonsterListItem_Previews: PreviewProvider {
    static var previews: some View {
        MonsterListItem(
            monster: Monster(id: 1, name: "Monster", imageUrl: ""),
            onClick: {_ in }
        )
    }
}
