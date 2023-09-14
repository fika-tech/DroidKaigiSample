import SwiftUI

struct PageLoadingIndicatorItem: View {
    var body: some View {
        ZStack(alignment: .center) {
            LoadingIndicator()
        }
        .frame(height: 54)
        .background(Color.white)
    }
}

struct PageLoadingIndicatorItem_Previews: PreviewProvider {
    static var previews: some View {
        PageLoadingIndicatorItem()
    }
}
